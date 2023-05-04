package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.chapter;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.preview;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface chapterService {

    List<chapter> getCourseAllChapter(course course);

    void addChapterPPT(chapter chapter);

    void createChapter(chapter chapter);

    void deleteChapter(chapter chapter);

    void releasePreView(preview preview);
}
