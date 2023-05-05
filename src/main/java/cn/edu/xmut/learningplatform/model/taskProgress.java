package cn.edu.xmut.learningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class taskProgress {
    private Integer taskId;
    private Integer userId;
    private Double progress;

    public taskProgress(taskProgress taskProgress, Integer userId) {
        this.taskId = taskProgress.getTaskId();
        this.userId = userId;
        this.progress = taskProgress.getProgress();
    }
}
