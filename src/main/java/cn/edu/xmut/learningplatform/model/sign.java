package cn.edu.xmut.learningplatform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Locale;

@Data
@NoArgsConstructor
public class sign {
    private Integer id;
    private Integer courseId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private Integer publisherUserId;


    private Integer duration;

    private String signCode;
    private Long expire;

    public sign(Integer courseId, Date startTime, Date endTime, Integer publisherUserId, String signCode) {
        this.courseId = courseId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.publisherUserId = publisherUserId;
        this.signCode = signCode;
    }

    public int current;
    public int pageSize;

    public int total;
    public int signCount;
}

