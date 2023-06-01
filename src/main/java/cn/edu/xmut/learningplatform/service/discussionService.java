package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.classify;
import cn.edu.xmut.learningplatform.model.comment;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.discussion;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.Set;

public interface discussionService {
    //发布讨论
    void addDiscussion(discussion discussion);

    //根据分类查找讨论区
    PageInfo<discussion> selectDiscussionById(classify classify);
    //删除讨论
    void deleteDiscussionById(discussion discussion);
    //修改讨论
    void updateDiscussionById(discussion discussion);
    //查询某个讨论上的所有评论
    ArrayList<comment> selectCommentById(discussion discussion);

}
