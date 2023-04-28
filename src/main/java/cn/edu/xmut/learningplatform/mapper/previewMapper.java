package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.preview;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface previewMapper {
    /**
     * 添加预习
     * @param preView
     */
    void addPreView(preview preView);

    /**
     * 根据章节ID查询预习信息
     * @param chapterId
     */
    preview getPreViewByCourseIdAndChapterId(Integer courseId,Integer chapterId);
}
