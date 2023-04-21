package cn.edu.xmut.learningplatform.mapper;
import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface roleMapper {
    /**
     * 查询所有角色列表
     * @return
     */
    List<role> getRoleList(role role);

    List<permission> getRoleAllowPermissionList(int roleId);


    List<permission> getRoleNotAllowPermissionList(int roleId);


    void createRole(role role);


    role getRoleBySn(String sn);
    role getRoleByID(Integer id);

    int deleteRole(role role);

    List<role> getRoleListByPermission(int permissionId);


    void insertRolePermissions(@Param("roleId") int roleId, @Param("permissionIds") String[] permissionIds);

    void deletePermissionsByRoleId(@Param("roleId") int roleId);
}
