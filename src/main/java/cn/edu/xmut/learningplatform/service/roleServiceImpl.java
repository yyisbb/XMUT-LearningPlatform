package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.Check;
import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.rolePermission;
import cn.edu.xmut.learningplatform.model.user;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.xmut.learningplatform.mapper.roleMapper;
import cn.edu.xmut.learningplatform.mapper.userMapper;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class roleServiceImpl implements roleService {
    @Autowired
    private roleMapper roleMapper;
    @Autowired
    private userMapper userMapper;

    @Override
    public PageInfo<role> getRoleList(role role) {

        PageHelper.startPage(role.getCurrent(), role.getPageSize());
        List<role> allRole = roleMapper.getRoleList(role);

        // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
        PageInfo<role> pageInfo = new PageInfo<>(allRole);

        return pageInfo;
    }

    @Override
    public List<rolePermission> getRolePermissionList(int roleId) {
        return roleMapper.getRolePermissionList(roleId);
    }

    @Override
    public void createRole(role role) {
        if (ObjectUtils.isEmpty(role)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (StringUtils.isEmpty(role.getName()) || StringUtils.isEmpty(role.getSn())) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (!role.getName().matches(Check.NAME_REGEX)) {
            throw new GlobalException(ErrorCode.ROLE_NAME_FORMAT_ERROR);
        }

        role.setCreateTime(new Date());

        //查询是否存在
        role roleBySn = roleMapper.getRoleBySn(role.getSn());
        if (!ObjectUtils.isEmpty(roleBySn)) {
            throw new GlobalException(ErrorCode.ROLE_EXIST_ERROR);
        }

        roleMapper.createRole(role);
        if (role.getId() == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public void deleteRole(role role) {
        if (ObjectUtils.isEmpty(role)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (StringUtils.isEmpty(role.getSn())) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询是否存在
        role roleBySn = roleMapper.getRoleBySn(role.getSn());
        if (ObjectUtils.isEmpty(roleBySn)) {
            throw new GlobalException(ErrorCode.ROLE_NOT_EXIST_ERROR);
        }

        //查询是否有关联
        //权限
        List<rolePermission> permissionList = roleMapper.getRolePermissionList(roleBySn.getId());
        if (permissionList.size()!=0){
            throw new GlobalException(ErrorCode.ROLE_EXIST_PERMISSION_ERROR);
        }
        //用户
        List<user> userListByRole = userMapper.getUserListByRole(roleBySn.getId());
        if (userListByRole.size()!=0){
            throw new GlobalException(ErrorCode.ROLE_EXIST_PERMISSION_ERROR);
        }

        //存在就删除
        int line = roleMapper.deleteRole(role);

        if (line == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }


}
