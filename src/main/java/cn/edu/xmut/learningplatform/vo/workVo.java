package cn.edu.xmut.learningplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class workVo {
    private Integer courseId;
    private Integer workId;
    public int current;
    public int pageSize;
}
