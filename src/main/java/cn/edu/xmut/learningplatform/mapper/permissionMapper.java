package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface permissionMapper {
    /**
     * 获取所有权限
     * @return
     */
    List<permission> getPermissionList();
}
