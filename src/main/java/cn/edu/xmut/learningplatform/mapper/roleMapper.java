package cn.edu.xmut.learningplatform.mapper;
import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.rolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface roleMapper {
    /**
     * 查询所有角色列表
     * @return
     */
    List<role> getRoleList(role role);

    List<rolePermission> getRolePermissionList(int roleId);

    void createRole(role role);


    role getRoleBySn(String sn);

    int deleteRole(role role);

    List<role> getRoleListByPermission(int permissionId);
}
