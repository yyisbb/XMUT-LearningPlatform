package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.discussion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface discussionMapper {

    //添加讨论
    void addDiscussion(discussion discussion);
    //通過課程查询讨论
    List<discussion> selectDiscussionById(Integer id);
    //删除讨论
    void deleteDiscussionById(Integer id);
    //修改讨论
    void updateDiscussionById(discussion discussion);


}
