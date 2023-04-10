package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private  userMapper userMapper;

    @Override
    public List<user> queryUserList() {
        return userMapper.queryUserList();
    }
}
