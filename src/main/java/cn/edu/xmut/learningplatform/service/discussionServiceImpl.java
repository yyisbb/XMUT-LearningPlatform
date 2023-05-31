package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.commentMapper;
import cn.edu.xmut.learningplatform.mapper.discussionMapper;
import cn.edu.xmut.learningplatform.model.comment;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.discussion;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class discussionServiceImpl implements discussionService{
    @Autowired
    private discussionMapper discussionMapper;
    @Autowired
    private commentMapper commentMapper;

    //发布讨论
    @Override
    public void addDiscussion(discussion discussion) {
        if (ObjectUtils.isEmpty(discussion)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        discussionMapper.addDiscussion(discussion);
    }
    //查询该课程的所有讨论
    @Override
    public PageInfo<discussion> selectDiscussionById(course course) {
        if (ObjectUtils.isEmpty(course)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //校验参数
        if (course.getId() == null || course.getId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        List<discussion> discussions = discussionMapper.selectDiscussionById(course.getId());
        if (ObjectUtils.isEmpty(discussions)) {
            throw new GlobalException(ErrorCode.Discussion_EMPTY_ERROR);
        }
        return new PageInfo<>(discussions);
    }
    //删除讨论
    @Override
    public void deleteDiscussionById(discussion discussion) {
        if (ObjectUtils.isEmpty(discussion)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        if (discussion.getId() == null || discussion.getId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        discussionMapper.deleteDiscussionById(discussion.getId());
    }
    //修改讨论
    @Override
    public void updateDiscussionById(discussion discussion) {
        if (ObjectUtils.isEmpty(discussion)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        discussionMapper.updateDiscussionById(discussion);
    }
    //查询某个课程下的某条讨论的所有评论
    @Override
    public ArrayList<comment> selectCommentById(discussion discussion) {
        if (ObjectUtils.isEmpty(discussion)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        if (discussion.getId() == null || discussion.getId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        return  commentMapper.selectCommentById(discussion.getId());
    }


}
