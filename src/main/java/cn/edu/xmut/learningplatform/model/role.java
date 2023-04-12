package cn.edu.xmut.learningplatform.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description role
 * @author zwj
 * @date 2023-04-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色创建时间
     */
    private Date createTime;

    /**
     * 角色修改时间
     */
    private Date updateTime;

}