package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.model.userRole;
import com.github.pagehelper.PageInfo;

public interface userService {
    String login(user user);

    user getUserByUserName(String username);

    user getUserByUserId(Integer userId);

    void register(user user);

    userRole getUserRole(int userId);

    PageInfo<user> getAllUser(user user);

    void updateStatus(String username);

    void insertUserRole(Integer userId, Integer roleId);

    void insertCourse(Integer id, Integer courseId);

}
