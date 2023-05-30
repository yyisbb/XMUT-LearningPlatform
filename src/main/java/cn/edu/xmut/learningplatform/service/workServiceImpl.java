package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.workMapper;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.mapper.chapterMapper;
import cn.edu.xmut.learningplatform.model.*;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import cn.edu.xmut.learningplatform.vo.workVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 李大大
 * @version 1.0
 */
@Service
public class workServiceImpl implements workService {
    @Autowired
    private workMapper workMapper;
    @Autowired
    private courseMapper courseMapper;
    @Autowired
    private chapterMapper chapterMapper;
    public PageInfo<works> getWorkStatus(workVo workVo, List<works> worksList) {
        if (ObjectUtils.isEmpty(worksList)) {
            throw new GlobalException(ErrorCode.WORK_EMPTY_ERROR);
        }
        userWork userWork;
        for (works works : worksList) {
            workVo.setWorkId(works.getId());
            userWork = workMapper.getWorkStatus(workVo);
            if (userWork == null) {
                works.setStatus(0);
            } else if (userWork.getUpFilePath() != null || userWork.getComment() != null) {
                works.setStatus(1);
            } else {
                works.setStatus(-1);
            }

        }
        return new PageInfo<>(worksList);
    }

    /**
     * 根据当前学生id查询所有课程作业
     */
    @Override
    public PageInfo<works> getStudentAllWork(user loginUser) {
        workVo workVo = new workVo();
        workVo.setUserId(loginUser.getId());
        List<works> worksList = workMapper.getWorkByStudentId(loginUser.getId());
        for (works works : worksList) {
            works.setChapter(chapterMapper.getChapterById(works.getChapterId()));
            works.setCourse(courseMapper.getCourseByCourseId(works.getCourseId()));
        }
        return getWorkStatus(workVo, worksList);
    }

    /**
     * 教师添加作业
     */
    @Override
    public void addWork(works works) {
        //校验参数
        if (works.getName() == null || works.getName().length() == 0
                || works.getStartTime() == null
                || works.getEndTime() == null
                || works.getChapterId() == null
                || works.getCourseId() == null
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        // TODO 直接调接口的时候要判断是否为已存在的老师?
        workMapper.addWork(works);
    }

    /**
     * 教师删除作业
     */
    @Override
    public void delWork(workVo workVo) {
        //参数判空
        if (workVo.getCourseId() == null || workVo.getCourseId() == 0
                || workVo.getChapterId() == null|| workVo.getChapterId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        workMapper.delWork(workVo);
    }

    /**
     * 教师修改作业
     */
    @Override
    public void editWork(works works) {
        //校验参数
        if (works.getName() == null || works.getName().length() == 0
                || works.getStartTime() == null
                || works.getEndTime() == null
                || works.getChapterId() == null
                || works.getCourseId() == null
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //TODO 查询该章节是否存在作业存在就先删除后新增 实现 替换

        workMapper.editWork(works);
    }
    /**
     * 获取当前课程下所有作业
     */
    @Override
    public PageInfo<works> getCourseAllWork(workVo courseVo) {

        //参数
        if (ObjectUtils.isEmpty(courseVo)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //判空
        if (courseVo.getCourseId() == null || courseVo.getCourseId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询课程
        course sqlCourse = courseMapper.getCourseByCourseId(courseVo.getCourseId());
        //判断课程是否存在
        if (sqlCourse == null || sqlCourse.getId() == 0) {
            throw new GlobalException(ErrorCode.COURSE_EMPTY_ERROR);
        }

        //带参则分页 否则不分页
        if (courseVo.getCurrent() != 0 && courseVo.getPageSize() != 0) {
            PageHelper.startPage(courseVo.getCurrent(), courseVo.getPageSize());
        }

        //查询作业
        List<works> worksList = workMapper.getWorkByCourseId(courseVo.getCourseId());

        //查询每个作业的章节
        for (works works : worksList) {
            works.setChapter(chapterMapper.getChapterById(works.getChapterId()));
            works.setCourse(courseMapper.getCourseByCourseId(works.getCourseId()));
        }
        //作业状态
/*        user loginUser = UserUtil.getLoginUser();
        workVo work = new workVo();
        work.setUserId(loginUser.getId());*/
        return new PageInfo<>(worksList);
    }
    /**
     * 查看作业详情
     */
    @Override
    public works getWorkByWorkId(Integer workId) {
        //参数校验
        if (workId == null || workId == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //查询作业
        works sqlWork = workMapper.getWorkByWorkId(workId);
        sqlWork.setChapter(chapterMapper.getChapterById(sqlWork.getChapterId()));
        sqlWork.setCourse(courseMapper.getCourseByCourseId(sqlWork.getCourseId()));
        if (ObjectUtils.isEmpty(sqlWork)) {
            throw new GlobalException(ErrorCode.WORK_EMPTY_ERROR);
        }
        return sqlWork;
    }

    /**
     * 获取学生提交的作业
     */
    @Override
    public PageInfo<userWork> getSubmitWork(workVo workVo) {
        //参数校验
        if (workVo.getCourseId() == null || workVo.getCourseId() == 0||
                workVo.getChapterId() == null || workVo.getChapterId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //获取作业Id
        workVo.setWorkId(workMapper.getWorkId(workVo));
        //查询作业
        List<userWork> userWorkList = workMapper.getSubmitWork(workVo);
        return new PageInfo<>(userWorkList);
    }
    /**
     * 批改作业/打分评价
     * userId workId
     * @param userWork
     */
    @Override
    public void correctWork(userWork userWork) {
        //参数校验
        if (userWork.getScore() == null){
            throw new GlobalException(ErrorCode.SCORE_EMPTY_ERROR);
        }
        workMapper.correctWork(userWork);
    }
    /**
     * 学生第一次点开作业
     */
    @Override
    public void viewWork(workVo workVo) {
        //第一次点开作业 -->继续作业
        //参数校验
        if (workVo.getWorkId() == null || workVo.getWorkId() == 0
           ||workVo.getUserId() == null || workVo.getUserId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        workMapper.viewWork(workVo);
    }
    /**
     * 模糊
     */
    @Override
    public PageInfo<works> getWorkByBlur(workVo workVo) {
        user loginUser = UserUtil.getLoginUser();
        workVo work = new workVo();
        work.setUserId(loginUser.getId());
        List<works> worksList = workMapper.getWorkByBlur(workVo);
        //判断当前作业完成状态 userId workId
        return getWorkStatus(work, worksList);
    }
    /**
     * 交作业
     */
    @Override
    public void doWork(workVo workVo) {
        //参数校验
        if (workVo.getWorkId() == null || workVo.getWorkId() == 0
                ||workVo.getUserId() == null || workVo.getUserId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        if(workVo.getUpFilePath()==null && workVo.getComment()==null){
            throw new GlobalException(ErrorCode.WORKUP_EMPTY_ERROR);
        }
        //没有这份作业时
        if (workMapper.doWork(workVo)==0){
            throw new GlobalException(ErrorCode.WORK_EMPTY_ERROR);
        }
    }
}
