package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.comment;

import java.util.ArrayList;
import java.util.Set;

public interface commentService {
    //发布评论
    void addComment(comment comment);
    //展开回复
    ArrayList<comment> expandComment(Integer parentId, Set<Integer> queriedCommentIds);
    //删除评论
    void deleteComment(comment comment );
    //修改评论
    void updateComment(comment comment);
    //添加子评论
    void addSubComment(comment comment);

}
