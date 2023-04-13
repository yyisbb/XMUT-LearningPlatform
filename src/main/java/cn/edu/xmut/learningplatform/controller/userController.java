package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.annotation.AuthPass;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.model.userRole;
import cn.edu.xmut.learningplatform.service.userService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService userService;

    @PostMapping("/login")
    @AuthPass
    public ResultUtil<String> login(@RequestBody user user) {
        return ResultUtil.success(userService.login(user));
    }

    @PostMapping("/register")
    @AuthPass
    public ResultUtil<String> register(@RequestBody user user) {
        userService.register(user);
        return ResultUtil.success();
    }


    @PostMapping("/getUserInfo")
    public ResultUtil<user> getUserInfo() {
        return ResultUtil.success(UserUtil.getLoginUser());
    }

    @PostMapping("/getUserRole")
    public ResultUtil<List<userRole>> getUserRole() {
        return ResultUtil.success(userService.getUserRole(UserUtil.getLoginUser().getId()));
    }
}
