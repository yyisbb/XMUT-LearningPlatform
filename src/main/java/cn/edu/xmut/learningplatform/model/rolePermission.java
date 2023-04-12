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
     * role_id
     */
    private Integer roleId;

    /**
     * permission_id
     */
    private Integer permissionId;

}
