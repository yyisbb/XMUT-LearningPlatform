package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.discussion;
import cn.edu.xmut.learningplatform.model.likes;

import java.util.ArrayList;

public interface likesService {

    void likesDiscussion(discussion discussion);
    //取消点赞
    void cancelLikes(likes likes);

    ArrayList<discussion> selectDiscussion(Integer limit);

    ArrayList<discussion> selectLikesDiscussion();
}
