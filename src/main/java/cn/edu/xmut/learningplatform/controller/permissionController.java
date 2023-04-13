package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.service.permissionService;
import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResultUtil<List<permission>> getPermissionList(){
        return  ResultUtil.success(permissionService.getPermissionList());
    }
}
