package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.discussion;
import cn.edu.xmut.learningplatform.model.likes;

import java.util.ArrayList;

public interface likesService {
    //点赞文章
    void likesDiscussion(discussion discussion);
    //取消点赞
    void cancelLikes(discussion discussion);
    //点赞数为前limit的讨论
    ArrayList<discussion> selectDiscussion();
    //查询用户点赞的所有讨论
    ArrayList<discussion> selectLikesDiscussion();
}
