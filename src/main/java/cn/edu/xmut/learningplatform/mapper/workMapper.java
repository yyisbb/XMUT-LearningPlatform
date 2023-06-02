package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.mutual;
import cn.edu.xmut.learningplatform.model.userWork;
import cn.edu.xmut.learningplatform.model.works;
import cn.edu.xmut.learningplatform.vo.workVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李大大
 * @version 1.0
 */
@Mapper
public interface workMapper {

    List<works> getWorkByStudentId(@Param("userId") Integer userId);

    List<works> getWorkByCourseId(workVo workVo);

    void addWork(works works);

    void delWork(workVo workVo);

    void editWork(works works);

    works getWorkDetails(workVo workVo);

    List<userWork> getSubmitWork(workVo workVo);

    void correctWork(userWork userWork);

    void viewWork(workVo workVo);

    userWork getWorkStatus(workVo workVo);

    int doWork(workVo workVo);
    int updateMutual(Integer mutual,Integer workId);
    int insertMutual(mutual mutual);


    List<userWork> getMutualWork(mutual mutual);

    int mutualCorrectWork(mutual mutual);
    mutual getMutual(mutual mutual);

    Integer computeScore(mutual mutual);
}
