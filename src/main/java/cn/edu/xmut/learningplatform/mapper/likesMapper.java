package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.discussion;
import cn.edu.xmut.learningplatform.model.likes;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface likesMapper {
    Integer selectStatusById(Integer id,Integer userId);

    void updateDiscussionById(Integer id);

    void updateStatusById(Integer id,Integer userId);

    //查询点赞数为前10的讨论
    ArrayList<discussion> selectDiscussion();

    void updateLikes(Integer id, Integer userId);

    ArrayList<Integer> selectLikesDiscussion(Integer userId);

    discussion selectDiscussionByUserID(Integer id);

    void insertDiscussionById(Integer id, Integer userId);
}

