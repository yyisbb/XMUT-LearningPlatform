package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.Check;
import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.permissionMapper;
import cn.edu.xmut.learningplatform.mapper.roleMapper;
import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.model.role;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class permissionServiceImpl implements permissionService {
    @Autowired
    private permissionMapper permissionMapper;

    @Autowired
    private roleMapper roleMapper;

    @Override
    public PageInfo<permission> getPermissionList(permission permission) {
        if (permission.getCurrent()!=0&&permission.getPageSize()!=0){
            PageHelper.startPage(permission.getCurrent(), permission.getPageSize());
        }
        List<permission> allPermission = permissionMapper.getPermissionList(permission);
        return new PageInfo<>(allPermission);
    }

    @Override
    public void createPermission(permission permission) {
        if (ObjectUtils.isEmpty(permission)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (StringUtils.isEmpty(permission.getName()) || StringUtils.isEmpty(permission.getUrl()) || StringUtils.isEmpty(permission.getMethod())) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (!permission.getName().matches(Check.NAME_REGEX)) {
            throw new GlobalException(ErrorCode.PERMISSION_NAME_FORMAT_ERROR);
        }
        if (!permission.getUrl().matches(Check.URL_REGEX)) {
            throw new GlobalException(ErrorCode.PERMISSION_URL_FORMAT_ERROR);
        }

        if (!permission.getMethod().matches(Check.HTTP_METHOD_REGEX)) {
            throw new GlobalException(ErrorCode.PERMISSION_METHOD_FORMAT_ERROR);
        }

        permission.setCreateTime(new Date());

        //查询是否存在
        permission sqlPermission = permissionMapper.getPermission(permission);
        if (!ObjectUtils.isEmpty(sqlPermission)) {
            throw new GlobalException(ErrorCode.PERMISSION_EXIST_ERROR);
        }

        permissionMapper.createPermission(permission);
        if (permission.getId() == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public void deletePermission(permission permission) {
        if (ObjectUtils.isEmpty(permission)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (permission.getId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询是否存在
        permission sqlPermission = permissionMapper.getPermissionByPermissionId(permission.getId());
        if (ObjectUtils.isEmpty(sqlPermission)) {
            throw new GlobalException(ErrorCode.PERMISSION_NOT_EXIST_ERROR);
        }

        //查询是否有关联
        //角色与权限
        List<role> roleListByPermission = roleMapper.getRoleListByPermission(sqlPermission.getId());
        if (roleListByPermission.size()!=0){
            throw new GlobalException(ErrorCode.PERMISSION_EXIST_PERMISSION_ERROR);
        }
        //存在就删除
        int line = permissionMapper.deletePermission(permission.getId());

        if (line == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }
}
