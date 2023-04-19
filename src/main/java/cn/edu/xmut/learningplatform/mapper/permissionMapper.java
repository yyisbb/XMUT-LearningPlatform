package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.model.role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface permissionMapper {
    /**
     * 获取所有权限
     *
     * @return
     */
    List<permission> getPermissionList(permission permission);

    /**
     * 新增权限
     */
    void createPermission(permission permission);

    /**
     * 新增单个权限
     */
    permission getPermission(permission permission);
    permission getPermissionByPermissionId(int permissionId);

    int deletePermission(int permissionId);
}
