package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.comment;

import java.util.ArrayList;

public interface commentService {
    //发布评论
    void addComment(comment comment);

    ArrayList<comment> expandComment(Integer parentId);
    //删除评论
    void deleteComment(comment comment);
    //修改评论
    void updateComment(comment comment);
}
