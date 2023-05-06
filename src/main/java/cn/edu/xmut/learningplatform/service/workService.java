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

   PageInfo<works> getTeacherAllWork(user loginUser);

   void addWork(works works, user loginUser);

    void delWork(user loginUser, works works);

    void editWork(user loginUser, works works);
}
