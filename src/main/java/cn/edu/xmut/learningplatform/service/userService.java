package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.user;
import org.springframework.stereotype.Repository;

@Repository
public interface userService {
    String login(user user);

    user getUserByUserName(String username);

    void register(user user);
}
