package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.user;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userService {
    List<user> queryUserList();
}
