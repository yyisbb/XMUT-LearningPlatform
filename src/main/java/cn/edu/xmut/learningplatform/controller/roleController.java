package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.dto.rolePermission;
import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.dto.permissionList;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.service.roleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class roleController {
    @Autowired
    private roleService roleService;

    /**
     * 获取角色列表
     * @return
     */
    @PostMapping("/getRoleList")
    public ResultUtil<PageInfo<role>> getRoleList(@RequestBody role role) {
        return ResultUtil.success(roleService.getRoleList(role));
    }

    /**
     * 获取指定角色的权限列表
     */
    @PostMapping("/getRolePermissionList")
    public ResultUtil<permissionList> getRolePermissionList(@RequestBody role role) {
        return ResultUtil.success(roleService.getRolePermissionList(role.getId()));
    }


    /**
     * 新增角色
     * @param role
     * @return
     */
    @PostMapping("/createRole")
    public ResultUtil<String> createRole(@RequestBody role role) {
        roleService.createRole(role);
        return ResultUtil.success();
    }


    /**
     * 删除角色
     * @param role
     * @return
     */
    @PostMapping("/deleteRole")
    public ResultUtil<String> deleteRole(@RequestBody role role) {
        roleService.deleteRole(role);
        return ResultUtil.success();
    }


    @PostMapping("/insertRolePermissions")
    public ResultUtil<String> insertRolePermissions(@RequestBody rolePermission rolePermission){
        roleService.insertRolePermissions(rolePermission.getRoleId(),rolePermission.getPermissionIds());
        return ResultUtil.success();
    }

}
