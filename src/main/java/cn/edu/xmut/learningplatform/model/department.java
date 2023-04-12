package cn.edu.xmut.learningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * @description user_department
 * @author zwj
 * @date 2023-04-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门创建时间
     */
    private Date createTime;

    /**
     * 部门修改时间
     */
    private Date updateTime;

}