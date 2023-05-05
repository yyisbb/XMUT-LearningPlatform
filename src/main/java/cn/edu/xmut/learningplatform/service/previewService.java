package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.preview;
import cn.edu.xmut.learningplatform.model.taskProgress;
import com.github.pagehelper.PageInfo;

public interface previewService {
    /**
     * 学生查询所有预习课程
     * @param course
     * @return
     */
    PageInfo<preview> selectPreviewByCourseId(course course);

    /**
     * 修改进度
     * @param taskProgress
     */
    void updatePreviewProgress(taskProgress taskProgress);

    taskProgress getPreviewProgress(taskProgress taskProgress);
}
