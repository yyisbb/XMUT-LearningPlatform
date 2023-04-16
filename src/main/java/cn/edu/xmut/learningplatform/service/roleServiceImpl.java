package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.rolePermission;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.model.userRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.xmut.learningplatform.mapper.roleMapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class roleServiceImpl implements roleService{
    @Autowired
    private roleMapper roleMapper;
    @Override
    public PageInfo<role> getRoleList(role role) {

        PageHelper.startPage(role.getCurrent(), role.getPageSize());
        List<role> allRole = roleMapper.getRoleList(role);

        // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
        PageInfo<role> pageInfo= new PageInfo<>(allRole);

        return pageInfo;
    }

    @Override
    public List<rolePermission> getRolePermissionList(int roleId) {
        return roleMapper.getRolePermissionList( roleId);
    }


}
