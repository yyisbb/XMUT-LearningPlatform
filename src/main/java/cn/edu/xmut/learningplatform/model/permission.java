package cn.edu.xmut.learningplatform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class permission  implements Serializable {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 权限修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 请求方法/get/post
     */
    private String method;

    public int current;
    public int pageSize;

}
