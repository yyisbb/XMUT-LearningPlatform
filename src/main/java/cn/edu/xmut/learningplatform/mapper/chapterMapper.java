package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.chapter;
import cn.edu.xmut.learningplatform.model.course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface chapterMapper {
    /**
     * 根据课程ID查询所有章节
     */
    List<chapter> getAllChapterByCourseGroupId(String courseGroupId);


    /**
     * 根据章节ID查询指定章节
     */
    chapter getChapterById(Integer id);

    int updateChapter(chapter chapter);

    void createChapter(chapter chapter);

    int deleteChapter(chapter chapter);


}
