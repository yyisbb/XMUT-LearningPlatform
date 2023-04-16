package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.mapper.permissionMapper;
import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.model.role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class permissionServiceImpl implements permissionService{
    @Autowired
    private permissionMapper permissionMapper;
    @Override
    public PageInfo<permission> getPermissionList(permission permission) {
        PageHelper.startPage(permission.getCurrent(), permission.getPageSize());
        List<permission> allPermission =  permissionMapper.getPermissionList(permission);
        return new PageInfo<>(allPermission);
    }
}
