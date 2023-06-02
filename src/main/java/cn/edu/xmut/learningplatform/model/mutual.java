package cn.edu.xmut.learningplatform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class mutual {
    private Integer id;
    private Integer userId;
    private Integer gradedUserId;
    private Integer workId;
    private Integer grade;

    public mutual(Integer userId, Integer gradedUserId, Integer workId) {
        this.userId = userId;
        this.gradedUserId = gradedUserId;
        this.workId = workId;
    }

    public mutual(Integer userId, Integer workId) {
        this.userId = userId;
        this.workId = workId;
    }
}
