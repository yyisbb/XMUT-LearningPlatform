package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.sign;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.vo.signInVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface signService {

    void createSign(sign sign);

    PageInfo<sign> getSignListByCourseId(sign courseId);

    void signIn(String signCode);

    sign getSignBySignId(Integer signId);

    void changeSignCode(signInVo signInVo);

    List<user> getSignRecordBySignId(signInVo signInVo);
}
