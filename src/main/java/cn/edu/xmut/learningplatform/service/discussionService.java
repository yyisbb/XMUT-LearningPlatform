package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.comment;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.discussion;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;

public interface discussionService {
    //发布讨论
    void addDiscussion(discussion discussion);

    PageInfo<discussion> selectDiscussionById(course course);

    void deleteDiscussionById(discussion discussion);

    void updateDiscussionById(discussion discussion);

    ArrayList<comment> selectCommentById(discussion discussion);

}
