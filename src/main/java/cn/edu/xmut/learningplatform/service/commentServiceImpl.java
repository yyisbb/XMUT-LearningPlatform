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
    public ArrayList<comment> expandComment(Integer parentId) {
        if (ObjectUtils.isEmpty(parentId)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        return commentMapper.expandComment(parentId);
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
