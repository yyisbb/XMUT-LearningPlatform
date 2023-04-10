package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userMapper {
    List<user> queryUserList();
}
