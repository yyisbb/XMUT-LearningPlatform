package cn.edu.xmut.learningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class setting {
    private Integer id;
    private String key;
    private String value;
}
