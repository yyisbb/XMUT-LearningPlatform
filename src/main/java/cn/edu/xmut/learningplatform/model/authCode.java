package cn.edu.xmut.learningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class authCode implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String code;
    private String school;

    public int current;
    public int pageSize;
}
