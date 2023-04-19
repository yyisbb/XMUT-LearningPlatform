package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.service.permissionService;
import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class permissionController {
    @Autowired
    private permissionService permissionService;


    /**
     * 获取所有权限列表
     * @return
     */
    @PostMapping("/getPermissionList")
    public ResultUtil<PageInfo<permission>> getPermissionList(@RequestBody permission permission){
        return  ResultUtil.success(permissionService.getPermissionList(permission));
    }


    /**
     * 新增权限
     * @param permission
     * @return
     */
    @PostMapping("/createPermission")
    public ResultUtil<String> createPermission(@RequestBody permission permission) {
        permissionService.createPermission(permission);
        return ResultUtil.success();
    }


    /**
     * 删除权限
     * @param permission
     * @return
     */
    @PostMapping("/deletePermission")
    public ResultUtil<String> deletePermission(@RequestBody permission permission) {
        permissionService.deletePermission(permission);
        return ResultUtil.success();
    }
}
