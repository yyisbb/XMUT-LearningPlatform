package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.mutual;
import cn.edu.xmut.learningplatform.model.userWork;
import cn.edu.xmut.learningplatform.model.works;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.vo.workVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李大大
 * @version 1.0
 */
@Repository
public interface workService {

    PageInfo<works> getStudentAllWork(user loginUser);

    void addWork(works works);

    void delWork(workVo workVo);

    void editWork(works works);

    PageInfo<works> getCourseAllWork(workVo workVo);

    works getWorkDetails(workVo workVo);

    PageInfo<userWork> getSubmitWork(workVo workVo);

    void correctWork(userWork userWork);

    void mutualCorrectWork(mutual mutual);

    void doWork(workVo workVo);

    List<user> releaseMutual(mutual mutual);

    List<userWork> getMutualWork(mutual mutual);

    mutual computeScore(mutual mutual);
}
