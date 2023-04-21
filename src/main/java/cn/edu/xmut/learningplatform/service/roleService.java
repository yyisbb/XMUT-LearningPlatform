package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.rolePermission;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.edu.xmut.learningplatform.dto.permissionList;

@Repository
public interface roleService {
    /**
     * 查询所有角色列表
     *
     * @return
     */
    PageInfo<role> getRoleList(role role);

    permissionList getRolePermissionList(int roleId);

    void createRole(role role);

    void deleteRole(role role);

    void insertRolePermissions(Integer roleId,String[] permissionIds);
}
