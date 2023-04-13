package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.role;
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

    List<user> getUserInfoByUserNameAndStuID(String username, String studentId);

    List<userRole> getUserRole(int userId);
}
