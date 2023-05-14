package cn.edu.xmut.learningplatform.mapper;

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

    List<works> getWorkByCourseId( Integer courseId);

    void addWork(works works);

    void delWork(workVo workVo);

    void editWork(works works);

    works getWorkByWorkId(Integer workId);

    Integer getWorkId(workVo workVo);

    List<userWork> getSubmitWork(workVo workVo);

    void correctWork(userWork userWork);

    void viewWork(workVo workVo);

    List<works> getWorkByBlur(workVo workVo);

    userWork getWorkStatus(workVo workVo);

    int doWork(workVo workVo);
}
