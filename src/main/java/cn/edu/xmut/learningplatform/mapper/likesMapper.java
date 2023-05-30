package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.discussion;
import cn.edu.xmut.learningplatform.model.likes;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface likesMapper {
    Integer selectStatusById(discussion discussion,Integer userId);

    void updateDiscussionById(discussion discussion);

    void updateStatusById(likes likes);
    //查詢點讚數前limit的討論
    ArrayList<discussion> selectDiscussion(Integer limit);

    void updateLikes(discussion discussion, Integer userId);

    ArrayList<Integer> selectLikesDiscussion(Integer userId);

    discussion selectDiscussionByUserID(Integer id);

}

