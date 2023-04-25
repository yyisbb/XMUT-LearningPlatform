package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.chapter;
import cn.edu.xmut.learningplatform.model.task;
import cn.edu.xmut.learningplatform.mapper.taskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class taskServiceImpl implements taskService {
    @Autowired
    private taskMapper taskMapper;
    @Override
    public void addTask(task task) {
        //参数空
        if (ObjectUtils.isEmpty(task)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        if(
                task.getName()==null||task.getName().length()==0||
                        task.getChapterId()==null||task.getChapterId()==0
        ){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //创建章节
        taskMapper.addTask(task);
        if (task.getId()==null||task.getId()==0){
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public void updateTaskVideo(task task) {
        //参数空
        if (ObjectUtils.isEmpty(task)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //taskId为空
        if(task.getId()==null||task.getId()==0||task.getVideoUrl()==null||task.getVideoUrl().length()==0){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //任务点是否存在
        task sqlTask = taskMapper.getTaskById(task.getId());
        if (ObjectUtils.isEmpty(sqlTask)){
            throw new GlobalException(ErrorCode.TASK_EMPTY_ERROR);
        }

        //设置Video Url
        if (taskMapper.updateTask(task)==0){
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public void deleteTask(task task) {
        //参数空
        if (ObjectUtils.isEmpty(task)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //参数校验
        if(
                task.getId()==null||task.getId()==0
        ){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询任务点是否存在
        if (ObjectUtils.isEmpty(taskMapper.getTaskById(task.getId()))){
            throw new GlobalException(ErrorCode.TASK_EMPTY_ERROR);
        }


        //TODO 如果有作业 再看看要不要做其他的

        if (taskMapper.deleteTask(task)==0){
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }
}
