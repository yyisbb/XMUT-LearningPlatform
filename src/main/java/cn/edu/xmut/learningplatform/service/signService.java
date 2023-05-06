package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.sign;
import com.github.pagehelper.PageInfo;

public interface signService {

    void createSign(sign sign);

    PageInfo<sign> getSignListByCourseId(sign courseId);

    void signIn(String signCode);

    sign getSignBySignId(Integer signId);
}
