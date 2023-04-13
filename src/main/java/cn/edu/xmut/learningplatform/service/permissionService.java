package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.model.role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface permissionService {
    /**
     * 获取所有权限
     * @return
     */
    List<permission> getPermissionList();
}
