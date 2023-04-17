package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.model.userRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userMapper {
    /**
     * 登录查询 成功返回当前对象
     * @param user
     * @return
     */
    user login(user user);

    user getUserInfoByUserName(String username);

    void register(user user);

    List<user> getUserInfoByUserNameOrStuID(String username, String studentId);

    userRole getUserRole(int userId);

    List<user> getAllUser(user user);

    /**
     * 新增用户和角色的实体关系
     */
    void createUserRole(int userId, int roleId);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(user user);

}
