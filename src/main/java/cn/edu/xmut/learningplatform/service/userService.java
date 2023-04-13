package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.model.userRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userService {
    String login(user user);

    user getUserByUserName(String username);

    void register(user user);

    List<userRole> getUserRole(int userId);
}
