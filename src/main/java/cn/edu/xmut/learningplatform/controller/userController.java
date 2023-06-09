package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.annotation.AuthPass;
import cn.edu.xmut.learningplatform.model.*;
import cn.edu.xmut.learningplatform.service.userService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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


    @PostMapping("/getAllUser")
    ResultUtil<PageInfo<user>> getAllUser(@RequestBody user user) {
        return ResultUtil.success(userService.getAllUser(user));
    }

    @PostMapping("/getUserRole")
    public ResultUtil<userRole> getUserRole() {
        return ResultUtil.success(userService.getUserRole(UserUtil.getLoginUser().getId()));
    }


    @PostMapping("/updateStatus")
    public ResultUtil<String> updateStatus(@RequestBody user user) {
        userService.updateStatus(user.getUsername());
        return ResultUtil.success();
    }

    /**
     * 修改用户角色
     * @param userRole
     * @return
     */
    @PostMapping("/insertUserRole")
    public ResultUtil<String> insertUserRole(@RequestBody userRole userRole){
        userService.insertUserRole(userRole.getUserId(),userRole.getRoleId());
        return ResultUtil.success();
    }

    /**
     * 学生加入课程
     *
     * @param userCourse
     * @return
     */
    @PostMapping("/joinCourse")
    public ResultUtil<String> joinCourse(@RequestBody userCourse userCourse) {
        userService.insertCourse(UserUtil.getLoginUser().getId(),userCourse.getCourseId());
        return ResultUtil.success();
    }



}
