package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.task;
import cn.edu.xmut.learningplatform.model.taskProgress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface taskMapper {
    /**
     * 查询章节下的所有任务点
     */
    List<task> getAllTaskByChapterId(Integer chapterId);


    void addTask(task task);

    task getTaskById(Integer id);

    int updateTask(task task);

    int deleteTask(task task);

    taskProgress getTaskProgressByTaskIdAndUserId(Integer taskId, Integer userId);
    Integer createTaskProgress(taskProgress taskProgress);

    Integer updateTaskProgress(taskProgress taskProgress);
}
