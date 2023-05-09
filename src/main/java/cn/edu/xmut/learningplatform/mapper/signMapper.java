package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.sign;
import cn.edu.xmut.learningplatform.model.signUser;
import cn.edu.xmut.learningplatform.model.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface signMapper {
    void createSign(sign sign);

    List<sign> getSignListByCourseId(Integer courseId);

    sign getSignBySignId(Integer signId);

    signUser getSignRecord(Integer signId, Integer userId);

    int signIn(signUser signUser);

    List<user> getSignUserList(Integer signId);

    int updateSignCode(Integer signId,String signCode);
}
