package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.rolePermission;
import cn.edu.xmut.learningplatform.model.user;
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
    public ResultUtil<List<rolePermission>> getRolePermissionList(@RequestParam("roleId") int roleId) {
        return ResultUtil.success(roleService.getRolePermissionList(roleId));
    }

}
