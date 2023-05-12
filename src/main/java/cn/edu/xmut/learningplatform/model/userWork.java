package cn.edu.xmut.learningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 李大大
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userWork {
    private Integer workId;
    private Integer userId;
    private Integer status;
    private String upFilePath;
    private Integer score;
    private String comment;
    private String feedback;
}
