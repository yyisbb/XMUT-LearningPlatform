package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.preview;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

public interface previewService {
    /**
     * 学生查询所有预习课程
     * @param course
     * @return
     */
    PageInfo<preview> selectPreviewByCourseId(course course);
}
