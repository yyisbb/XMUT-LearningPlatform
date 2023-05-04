package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.task;
import org.springframework.stereotype.Repository;

public interface taskService {
    void addTask(task task);

    void updateTaskVideo(task task);

    void deleteTask(task task);
}
