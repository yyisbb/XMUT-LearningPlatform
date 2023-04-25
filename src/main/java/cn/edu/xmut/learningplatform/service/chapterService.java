package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.chapter;
import cn.edu.xmut.learningplatform.model.preview;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface chapterService {

    List<chapter> getCourseAllChapter(chapter chapter);

    void addChapterPPT(chapter chapter);

    void createChapter(chapter chapter);

    void deleteChapter(chapter chapter);

    void releasePreView(preview preview);
}
