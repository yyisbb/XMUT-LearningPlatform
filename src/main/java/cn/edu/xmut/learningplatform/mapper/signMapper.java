package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.sign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface signMapper {
    void createSign(sign sign);

    List<sign> getSignListByCourseId(Integer courseId);
}
