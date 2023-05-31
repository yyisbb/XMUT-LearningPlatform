package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.commentMapper;
import cn.edu.xmut.learningplatform.mapper.discussionMapper;
import cn.edu.xmut.learningplatform.model.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class commentServiceImpl implements commentService {
    @Autowired
    private commentMapper commentMapper;

    //发布评论
    @Override
    public void addComment(comment comment) {
        if (ObjectUtils.isEmpty(comment)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        commentMapper.addComment(comment);
    }
    //展开回复
    @Override
    public ArrayList<comment> expandComment(Integer parentId,Set<Integer> queriedCommentIds) {
        ArrayList<comment> childComments = commentMapper.expandComment(parentId);
        System.out.println(childComments);
        for (comment childComment : childComments) {
            if (!queriedCommentIds.contains(childComment.getId())) {
                queriedCommentIds.add(childComment.getId());
                ArrayList<comment> grandchildComments = expandComment(childComment.getId(), queriedCommentIds);
                childComment.setChildComments(grandchildComments);
            }
        }
        return childComments;
    }
    //删除评论
    @Override
    public void deleteComment(comment comment) {
        if (ObjectUtils.isEmpty(comment)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        commentMapper.deleteComment(comment.getId());
    }
    //修改评论
    @Override
    public void updateComment(comment comment) {
        if (ObjectUtils.isEmpty(comment)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        commentMapper.updateComment(comment);
    }
}
