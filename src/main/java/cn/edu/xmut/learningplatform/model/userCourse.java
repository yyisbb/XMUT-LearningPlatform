package cn.edu.xmut.learningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userCourse {

    private Integer userId;
    private Integer courseId;

}

