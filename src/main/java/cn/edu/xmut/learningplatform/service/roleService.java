package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.rolePermission;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface roleService {
    /**
     * 查询所有角色列表
     * @return
     */
    PageInfo<role> getRoleList(role role);

    List<rolePermission> getRolePermissionList(int roleId);

    void createRole(role role);

    void deleteRole(role role);
}
