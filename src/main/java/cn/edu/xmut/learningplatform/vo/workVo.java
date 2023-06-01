package cn.edu.xmut.learningplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class workVo {
    private String name;
    private Integer courseId;
    private Integer chapterId;
    private Integer userId;
    private Integer workId;
    private String upFilePath;
    private String comment;
    public int current;
    public int pageSize;

    public workVo(Integer courseId, Integer chapterId) {
        this.courseId = courseId;
        this.chapterId = chapterId;
    }
    private Integer status;

    public workVo(Integer workId) {
        this.workId = workId;
    }
}
