package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.mapper.permissionMapper;
import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.model.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class permissionServiceImpl implements permissionService{
    @Autowired
    private permissionMapper permissionMapper;
    @Override
    public List<permission> getPermissionList() {
        return permissionMapper.getPermissionList();
    }
}
