package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.classify;
import cn.edu.xmut.learningplatform.model.comment;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.discussion;
import cn.edu.xmut.learningplatform.service.courseService;
import cn.edu.xmut.learningplatform.service.discussionService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/discussion")
public class discussionController {

    @Autowired
    private discussionService discussionService;
    //发布讨论
    @PostMapping("/addDiscussion")
    public ResultUtil<String> addDiscussion(@RequestBody discussion discussion) {
         discussionService.addDiscussion(discussion);
        return ResultUtil.success();
    }

    //根据分类查找讨论区
    @PostMapping("/selectDiscussionById")
    public ResultUtil<PageInfo<discussion>> selectDiscussionById(@RequestBody classify classify) {
        return ResultUtil.success(discussionService.selectDiscussionById(classify));
    }

    //删除讨论
    @PostMapping("/deleteDiscussionById")
    public ResultUtil<String> deleteDiscussionById(@RequestBody discussion discussion) {
        discussionService.deleteDiscussionById(discussion);
        return ResultUtil.success();
    }
    //修改讨论
    @PostMapping("/updateDiscussionById")
    public ResultUtil<String> updateDiscussionById(@RequestBody discussion discussion) {
        discussionService.updateDiscussionById(discussion);
        return ResultUtil.success();
    }
    //查询某个讨论上的所有评论
    @PostMapping("/selectCommentById")
    public ResultUtil<ArrayList<comment>> selectCommentById(@RequestBody discussion discussion){
        return ResultUtil.success(discussionService.selectCommentById(discussion));
    }
}
