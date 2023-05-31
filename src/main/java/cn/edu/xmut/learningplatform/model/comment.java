package cn.edu.xmut.learningplatform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class comment {
    private Integer id;
    private String  commentContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date commentDate;
    private Integer commentUserId;
    private Integer discussionId;
    private Integer parentId;
    private List<comment> childComments;

}
