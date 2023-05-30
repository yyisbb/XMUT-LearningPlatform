package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.commentMapper;
import cn.edu.xmut.learningplatform.mapper.likesMapper;
import cn.edu.xmut.learningplatform.mapper.roleMapper;
import cn.edu.xmut.learningplatform.model.discussion;
import cn.edu.xmut.learningplatform.model.likes;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@Service
public class likesServiceImpl implements likesService{
    @Autowired
    private likesMapper likesMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;
    //点赞一条文章
    @Override
    public void likesDiscussion(discussion discussion) {

        transactionTemplate.execute(status -> {
            try {
                if (ObjectUtils.isEmpty(discussion)) {
                    throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
                }
                Integer userId = UserUtil.getLoginUser().getId();

                //判断当前用户是否已经点赞该文章
                if((likesMapper.selectStatusById(discussion.getId(),userId))==1){
                    //点赞记录表中修改原來的点赞记录
                    likesMapper.updateLikes(discussion.getId(),userId);
                    //更新文章表中的点赞数量通过文章Id
                    likesMapper.updateDiscussionById(discussion.getId());
                }else if ((likesMapper.selectStatusById(discussion.getId(),userId))==0){
                    likesMapper.insertDiscussionById(discussion.getId(),userId);
                }
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
            return null;
        });


    }

    //取消点赞
    @Override
    public void cancelLikes(discussion discussion) {
        if (ObjectUtils.isEmpty(discussion)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        Integer userId = UserUtil.getLoginUser().getId();
        //取消点赞
        likesMapper.updateStatusById(discussion.getId(),userId);
    }
    //查询点赞数为前limit的文章
    @Override
    public ArrayList<discussion> selectDiscussion() {

        return  likesMapper.selectDiscussion();
    }
    //查詢該用戶點讚的所有討論
    @Override
    public ArrayList<discussion> selectLikesDiscussion() {
        ArrayList<discussion> discussions =new ArrayList<>();
        Integer userId = UserUtil.getLoginUser().getId();
        //查詢該用戶點讚的所有討論id
        ArrayList<Integer> discussionIds = likesMapper.selectLikesDiscussion(userId);
        for (Integer id : discussionIds) {
            discussion discussion = likesMapper.selectDiscussionByUserID(id);
            discussions.add(discussion);
        }
        return discussions;
    }

}
