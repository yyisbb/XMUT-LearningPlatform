package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.Check;
import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.authCodeMapper;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.utils.RandomStringUtil;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class courseServiceImpl implements courseService {
    @Autowired
    private courseMapper courseMapper;
    @Override
    public PageInfo<course> getAllCourse(course course) {
        if (course.getCurrent()!=0&&course.getPageSize()!=0){
            PageHelper.startPage(course.getCurrent(), course.getPageSize());
        }
        List<course> allAuthCode = courseMapper.getAllCourse(course);
        return new PageInfo<>(allAuthCode);
    }

    @Override
    public PageInfo<course> getTeacherAllCourse(course course) {
        if (course.getCurrent()!=0&&course.getPageSize()!=0){
            PageHelper.startPage(course.getCurrent(), course.getPageSize());
        }

        List<course> allCourse = courseMapper.getTeacherAllCourse(course);
        return new PageInfo<>(allCourse);
    }

    @Override
    public void addCourse(course course) {
        if (ObjectUtils.isEmpty(course)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //校验参数
        if (course.getName()==null||course.getName().length() == 0
                || course.getDescription()==null||course.getDescription().length() == 0 ||
                course.getStartTime() == null ||
                course.getEndTime() == null||
                course.getCover()==null||course.getCover().length() == 0||
                course.getClassName()==null||course.getClassName().length() == 0
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }


        //添加课程
        course.setUserId(UserUtil.getLoginUser().getId());
        //生成随机课程码
        course.setCourseCode(RandomStringUtil.generateRandomString(8));
        //如果前端有传课程组ID 说明是复制课程

        String groupId =RandomStringUtil.generateRandomString(10);

        if (course.getGroupId()!=null&&course.getGroupId().length()!=0){
            //否则直接引用 先查询课程组
            List<course> courseListByGroupId = courseMapper.getCourseByGroupId(course.getGroupId());

            if(courseListByGroupId==null||courseListByGroupId.size()==0){
                throw new GlobalException(ErrorCode.GROUP_EMPTY_ERROR);
            }

            groupId = course.getGroupId();
        }

        //生成随机课程组ID
        course.setGroupId(groupId);

        courseMapper.addCourse(course);

        if (ObjectUtils.isEmpty(course)||course.getId()==0){
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

    /**
     * 获取指定课程ID的课程
     * @param course
     * @return
     */
    @Override
    public course getCourseByCourseId(course course) {
        if (ObjectUtils.isEmpty(course)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //校验参数
        if (course.getId() == null||course.getId()==0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询课程
        course sqlCourse = courseMapper.getCourseByCourseId(course.getId());
        if (ObjectUtils.isEmpty(sqlCourse)){
            throw new GlobalException(ErrorCode.COURSE_EMPTY_ERROR);
        }


        return sqlCourse;
    }
}
