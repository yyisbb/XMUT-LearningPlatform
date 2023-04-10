package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService userService;

    @GetMapping("/queryUserList")
    public List<user> queryUserList(){
        return userService.queryUserList();
    }
}
