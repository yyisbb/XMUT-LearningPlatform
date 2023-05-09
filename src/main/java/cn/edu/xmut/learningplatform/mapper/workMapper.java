package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.model.works;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李大大
 * @version 1.0
 */
@Mapper
public interface workMapper {

     List<works> getWorkByStudentId(@Param("user_id") Integer userId);
     /**
      * 添加作业
      */
     void addWork(works works);

    void delWork(works works);

    void editWork(works works);
}
