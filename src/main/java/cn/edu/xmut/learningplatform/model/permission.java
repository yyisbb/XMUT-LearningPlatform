package cn.edu.xmut.learningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description permission
 * @author zwj
 * @date 2023-04-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * api地址
     */
    private String url;

    /**
     * 权限创建时间
     */
    private Date createTime;

    /**
     * 权限修改时间
     */
    private Date updateTime;

    /**
     * 请求方法/get/post
     */
    private String method;

}
