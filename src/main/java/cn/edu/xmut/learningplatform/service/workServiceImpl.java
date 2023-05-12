package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.workMapper;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.mapper.chapterMapper;
import cn.edu.xmut.learningplatform.model.*;
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


    /**
     * 此方法用于查询作业时 判断作业当前处于什么状态
     */
    public void  workStatus(){
        //如果userWork表upFilePath/comment字段有数据就表示已提交 作业时间并未截至 可以重新提交
    }
    /**
     * 根据当前学生id查询作业
     */
    @Override
    public PageInfo<works> getStudentAllWork(user loginUser) {
        List<works> worksList = workMapper.getWorkByStudentId(loginUser.getId());
        return new PageInfo<>(worksList);
    }


    /**
     * 教师添加作业
     */
    @Override
    public void addWork(works works) {
        if (ObjectUtils.isEmpty(works)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //校验参数
        if (works.getName() == null || works.getName().length() == 0
                || works.getStartTime() == null
                || works.getEndTime() == null
                || works.getChapterId() == null
                || works.getCourseId() == null
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
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
        //TODO 健壮性校验
        workMapper.editWork(works);
    }

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
            chapter sqlChapter = chapterMapper.getChapterById(works.getChapterId());
            works.setChapterName(sqlChapter.getName());
        }

        return new PageInfo<>(worksList);
    }
    /**
     * 查看作业详情
     */
    @Override
    public works getWorkByWorkId(Integer workId) {
        //参数判空
        if (workId == null || workId == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //查询作业
        works sqlWork = workMapper.getWorkByWorkId(workId);
        if (ObjectUtils.isEmpty(sqlWork)) {
            throw new GlobalException(ErrorCode.WORK_EMPTY_ERROR);
        }
        return sqlWork;
    }

    @Override
    public Integer getWorkId(workVo workVo) {
        //参数
        if (ObjectUtils.isEmpty(workVo)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //判空
        if (workVo.getCourseId() == null || workVo.getCourseId() == 0||
                workVo.getChapterId() == null || workVo.getChapterId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        Integer id =  workMapper.getWorkId(workVo);
        return id;
    }

    /**
     * 获取学生提交的作业
     */
    @Override
    public PageInfo<userWork> getSubmitWork(Integer id) {
        //查询作业
        List<userWork> userWorkList = workMapper.getSubmitWork(id);
        return new PageInfo<>(userWorkList);
    }


    /**
     * 批改作业/打分评价
     * userId workId
     * @param userWork
     */
    @Override
    public void correctWork(userWork userWork) {
        workMapper.correctWork(userWork);
    }
    /**
     * 学生写作业
     */
    @Override
    public void doWork(userWork userWork) {
        //第一次点开作业 -->继续作业 前端的事儿
        //参数
        if (ObjectUtils.isEmpty(userWork)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //判空
        if (userWork.getWorkId() == null || userWork.getWorkId() == 0
           ||userWork.getUserId() == null || userWork.getUserId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        workMapper.doWork(userWork);
    }
}
