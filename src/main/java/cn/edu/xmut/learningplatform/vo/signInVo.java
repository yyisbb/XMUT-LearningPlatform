package cn.edu.xmut.learningplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class signInVo {
    private String signCode;
    private Integer signId;
}
