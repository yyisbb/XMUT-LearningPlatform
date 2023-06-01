package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.comment;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.discussion;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface commentMapper {

    //添加评论
    void addComment(comment comment);

    //查询该讨论的所有评论
    ArrayList<comment> selectCommentById(Integer discussionId);

    //查询回复该条回复的所有直接子回复
    ArrayList<comment> expandComment(Integer parentId);
    //删除评论
    void deleteComment(Integer id);

    //修改评论
    void updateComment(comment comment);

    comment findById(Integer parentId);



}

