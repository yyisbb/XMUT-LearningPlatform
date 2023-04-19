package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.permission;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface permissionService {
    /**
     * 获取所有权限
     * @return
     */
    PageInfo<permission> getPermissionList(permission permission);

    void createPermission(permission permission);

    void deletePermission(permission permission);
}
