package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.rolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.xmut.learningplatform.mapper.roleMapper;
import java.util.List;

@Service
public class roleServiceImpl implements roleService{
    @Autowired
    private roleMapper roleMapper;
    @Override
    public List<role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public List<rolePermission> getRolePermissionList(int roleId) {
        return roleMapper.getRolePermissionList( roleId);
    }


}
