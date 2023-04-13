package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.model.rolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface roleService {
    /**
     * 查询所有角色列表
     * @return
     */
    List<role> getRoleList();

    List<rolePermission> getRolePermissionList(int roleId);
}
