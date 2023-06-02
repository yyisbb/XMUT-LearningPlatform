package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.comment;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.discussion;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface discussionMapper {

    //添加讨论
    void addDiscussion(discussion discussion);
    //通過分类id查询讨论
    List<discussion> selectDiscussionById(Integer id);
    //删除讨论
    void deleteDiscussionById(Integer id);
    //修改讨论
    void updateDiscussionById(discussion discussion);

    //查询所有讨论
    List<discussion> getDiscussion();

    ArrayList<comment> selectCommentById(Integer id);
    //查询该讨论的所有评论


}
