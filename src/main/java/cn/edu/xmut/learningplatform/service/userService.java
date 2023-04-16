package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.model.userRole;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userService {
    String login(user user);

    user getUserByUserName(String username);

    void register(user user);

    userRole getUserRole(int userId);

    PageInfo<user> getAllUser(user user);
}
