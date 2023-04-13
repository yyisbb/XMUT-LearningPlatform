package cn.edu.xmut.learningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description role_permission
 * @author zwj
 * @date 2023-04-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class rolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色标识
     */
    private String  roleSn;

    /**
     * 请求路由方法
     */
    private String  requestMethod;
    /**
     * 请求路由路径
     */
    private String  requestUrl;

    /**
     * 权限名
     */
    private String  permissionName;

}
