package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.works;
import cn.edu.xmut.learningplatform.model.user;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

/**
 * @author 李大大
 * @version 1.0
 */
@Repository
public interface workService {

   PageInfo<works> getStudentAllWork(user loginUser);


   void addWork(works works);

    void delWork( works works);

    void editWork( works works);
}
