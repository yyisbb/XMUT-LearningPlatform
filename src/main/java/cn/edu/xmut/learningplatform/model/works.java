package cn.edu.xmut.learningplatform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 李大大
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class works {
    private Integer id;
    private String name;
    private String detail;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private Integer courseId;
    private Integer chapterId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    private String filePath;
    private Integer userId;
}
