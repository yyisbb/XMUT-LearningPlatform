package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.sign;

import java.util.List;

public interface signService {


    void createSign(sign sign);

    List<sign> getSignListByCourseId(Integer courseId);

}
