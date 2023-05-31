package cn.edu.xmut.learningplatform.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class course {
    private Integer id;
    private String name;
    private String cover;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String description;
    private String courseCode;
    private String groupId;
    private String className;
    private Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public Integer current;
    public Integer pageSize;
    private user user;

    public course(course copyCouse) {
        this.name = copyCouse.name;
        this.cover = copyCouse.cover;
        this.startTime = copyCouse.startTime;
        this.endTime = copyCouse.endTime;
        this.description = copyCouse.description;
        this.groupId = copyCouse.groupId;
        this.userId = copyCouse.userId;
        this.createTime = copyCouse.createTime;
        this.updateTime = copyCouse.updateTime;
    }
}

