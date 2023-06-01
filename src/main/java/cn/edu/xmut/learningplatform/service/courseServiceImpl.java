package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.Check;
import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.dto.courseListDTO;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.authCodeMapper;
import cn.edu.xmut.learningplatform.mapper.userMapper;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.model.userCourse;
import cn.edu.xmut.learningplatform.utils.RandomStringUtil;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class courseServiceImpl implements courseService {
    @Autowired
    private courseMapper courseMapper;
    @Autowired
    private userMapper userMapper;

    @Override
    public courseListDTO getAllCourse(course course) {
        if (course.getCurrent() != 0 && course.getPageSize() != 0) {
            PageHelper.startPage(course.getCurrent(), course.getPageSize());
        }
        List<course> nowCourseList = courseMapper.getAllCourse(course);
        List<course> beforeCourseList = nowCourseList.stream().filter(course1 -> course1.getEndTime().compareTo(new Date()) < 0).collect(Collectors.toList());

        return new courseListDTO(nowCourseList, beforeCourseList);
    }

    @Override
    public courseListDTO getTeacherAllCourse(course course) {
        if (course.getCurrent() != 0 && course.getPageSize() != 0) {
            PageHelper.startPage(course.getCurrent(), course.getPageSize());
        }

        List<course> allTeacherCourse = courseMapper.getTeacherAllCourse(course);
        List<course> beforeCourseList = allTeacherCourse.stream().filter(course1 -> course1.getEndTime().compareTo(new Date()) < 0).collect(Collectors.toList());

        return new courseListDTO(allTeacherCourse, beforeCourseList);
    }

    @Override
    public void addCourse(course course) {
        if (ObjectUtils.isEmpty(course)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }


        //添加课程
        course.setUserId(UserUtil.getLoginUser().getId());

        //如果前端有传课程组ID 说明是复制课程
        String groupId = RandomStringUtil.generateRandomString(10);

        if (course.getGroupId() != null && course.getGroupId().length() != 0) {
            //如果有带组ID 校验班级名
            String courseName = course.getClassName();
            if (courseName == null || courseName.length() == 0) {
                throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
            }
            //否则直接引用 先查询课程组
            List<course> courseListByGroupId = courseMapper.getCourseByGroupId(course.getGroupId());

            if (courseListByGroupId == null || courseListByGroupId.size() == 0) {
                throw new GlobalException(ErrorCode.GROUP_EMPTY_ERROR);
            }
            //复制课程
            course = new course(courseListByGroupId.get(0));
            course.setClassName(courseName);
        } else {
            //生成随机课程组ID
            course.setGroupId(groupId);

            //校验参数
            if (course.getName() == null || course.getName().length() == 0
                    || course.getDescription() == null || course.getDescription().length() == 0 ||
                    course.getStartTime() == null ||
                    course.getEndTime() == null ||
                    course.getCover() == null || course.getCover().length() == 0 ||
                    course.getClassName() == null || course.getClassName().length() == 0
            ) {
                throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
            }
        }


        //生成随机课程码
        course.setCourseCode(RandomStringUtil.generateRandomString(8));
        courseMapper.addCourse(course);

        if (ObjectUtils.isEmpty(course) || course.getId() == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

    /**
     * 获取指定课程ID的课程
     *
     * @param course
     * @return
     */
    @Override
    public course getCourseByCourseId(course course) {
        if (ObjectUtils.isEmpty(course)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //校验参数
        if (course.getId() == null || course.getId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询课程
        course sqlCourse = courseMapper.getCourseByCourseId(course.getId());
        if (ObjectUtils.isEmpty(sqlCourse)) {
            throw new GlobalException(ErrorCode.COURSE_EMPTY_ERROR);
        }


        return sqlCourse;
    }


    /**
     * 学生获取所选的课程
     *
     * @param
     * @return
     */
    @Override
    public PageInfo<course> selectStudentCourse(course course) {

        ArrayList<course> courseList = new ArrayList<>();
        if (course.getCurrent() != null &&
                course.getPageSize() != null &&
                course.getCurrent() != 0 &&
                course.getPageSize() != 0
        ) {
            PageHelper.startPage(course.getCurrent(), course.getPageSize());
        }

        List<userCourse> sqlCourseList = courseMapper.selectByCourseIdOrUserId(UserUtil.getLoginUser().getId(), null);
        for (userCourse userCourse : sqlCourseList) {
            course sqlCourse = courseMapper.getCourseByCourseId(userCourse.getCourseId());
            user userInfoByUserId = userMapper.getUserInfoByUserId(sqlCourse.getUserId());
            sqlCourse.setUser(userInfoByUserId);
            if (sqlCourse != null) {
                courseList.add(sqlCourse);
            }
        }
        return new PageInfo<>(courseList);
    }

    @Override
    public void studentAddCourse(course course) {
        //查询课程是否存在
        //查询课程
        course sqlCourse = courseMapper.getCourseByCourseCode(course.getCourseCode());
        if (ObjectUtils.isEmpty(sqlCourse)) {
            throw new GlobalException(ErrorCode.COURSE_EMPTY_ERROR);
        }

        //查询学生是否已经加入了
        userCourse userCourse = new userCourse(UserUtil.getLoginUser().getId(), sqlCourse.getId());
        userCourse sqlUserCourse = courseMapper.getUserCourse(userCourse);
        if (!ObjectUtils.isEmpty(sqlUserCourse)) {
            throw new GlobalException(ErrorCode.USER_COURSE_EXIST_ERROR);
        }

        //学生加入课程
        int count = courseMapper.studentAddCourse(userCourse);
        if (count == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

}
