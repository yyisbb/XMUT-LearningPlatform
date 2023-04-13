package cn.edu.xmut.learningplatform.mapper;
import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.rolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface roleMapper {
    /**
     * 查询所有角色列表
     * @return
     */
    List<role> getRoleList();

    List<rolePermission> getRolePermissionList(int roleId);
}
