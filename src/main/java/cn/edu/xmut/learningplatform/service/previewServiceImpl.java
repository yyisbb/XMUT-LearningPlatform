package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.previewMapper;
import cn.edu.xmut.learningplatform.mapper.taskMapper;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.preview;
import cn.edu.xmut.learningplatform.model.task;
import cn.edu.xmut.learningplatform.model.taskProgress;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class previewServiceImpl implements previewService {
    @Autowired
    private previewMapper previewMapper;

    @Autowired
    private taskMapper taskMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;


    /**
     * 学生查询当前课程的预习点
     *
     * @param course
     * @return
     */
    @Override
    public PageInfo<preview> selectPreviewByCourseId(course course) {

        if (ObjectUtils.isEmpty(course)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (course.getId() == null || course.getId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (course.getCurrent() != null &&
                course.getPageSize() != null &&
                course.getCurrent() != 0 &&
                course.getPageSize() != 0
        ) {
            PageHelper.startPage(course.getCurrent(), course.getPageSize());
        }

        List<preview> previews = previewMapper.selectPreviewByCourseId(course.getId());
        if (ObjectUtils.isEmpty(previews) || previews.size() == 0) {
            throw new GlobalException(ErrorCode.PREVIEW_EMPTY_ERROR);
        }

        //根据每个预习任务 拿到对应的视频地址
        for (preview preview : previews) {
           preview.setVideoUrlList(taskMapper.getAllTaskByChapterId(preview.getChapterId()));
        }
        return new PageInfo<>(previews);
    }

    @Override
    public void updatePreviewProgress(taskProgress taskProgress) {
        if (ObjectUtils.isEmpty(taskProgress)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (taskProgress.getTaskId() == null ||
                taskProgress.getTaskId() == 0||
                taskProgress.getUserId()==null||
                taskProgress.getUserId()==0||
                taskProgress.getProgress()==null
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //修改进度
        //如果没有这条进度,就新增一条记录

        //开事务
        transactionTemplate.execute(status -> {
            try {
                //查询任务点
                task sqlTask = taskMapper.getTaskById(taskProgress.getTaskId());
                if (ObjectUtils.isEmpty(sqlTask)) {
                    throw new GlobalException(ErrorCode.TASK_EMPTY_ERROR);
                }

                //查询进度
                taskProgress sqlTaskProgress = taskMapper.getTaskProgressByTaskIdAndUserId(sqlTask.getId(), taskProgress.getUserId());
                //不存在就创建
                if (ObjectUtils.isEmpty(sqlTaskProgress)) {
                    //创建进度
                    Integer count = taskMapper.createTaskProgress(taskProgress);
                    if (count == null || count == 0) {
                        throw new GlobalException(ErrorCode.SQL_ERROR);
                    }
                }else {
                    //修改进度 取数据库和传来值中的较大值
                    //然后再取最大值100
                    taskMapper.updateTaskProgress(new taskProgress(taskProgress.getTaskId(), taskProgress.getUserId(),
                            Math.min(Math.max(taskProgress.getProgress(), sqlTaskProgress.getProgress()), 100)));
                }

            }catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
            return null;
        });
    }

    @Override
    public taskProgress getPreviewProgress(taskProgress taskProgress) {
        if (ObjectUtils.isEmpty(taskProgress)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (taskProgress.getTaskId() == null ||
                taskProgress.getTaskId() == 0||
                taskProgress.getUserId()==null||
                taskProgress.getUserId()==0
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询任务点
        task sqlTask = taskMapper.getTaskById(taskProgress.getTaskId());
        if (ObjectUtils.isEmpty(sqlTask)) {
            throw new GlobalException(ErrorCode.TASK_EMPTY_ERROR);
        }

        //查询进度
        taskProgress sqlTaskProgress = taskMapper.getTaskProgressByTaskIdAndUserId(sqlTask.getId(), taskProgress.getUserId());
        if (ObjectUtils.isEmpty(sqlTaskProgress)) {
            throw new GlobalException(ErrorCode.TASK_PROGRESS_EMPTY_ERROR);
        }
        return sqlTaskProgress;
    }
}
