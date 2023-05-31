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
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;
    private Integer courseId;
    private Integer chapterId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    private String filePath;

    private chapter chapter;
    private course course;
    private Integer status;// 0表示未查看过 -1表示已查看但没有任何作答 1表示已经作答
}

