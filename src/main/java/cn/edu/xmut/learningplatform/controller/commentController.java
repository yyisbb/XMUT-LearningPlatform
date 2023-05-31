package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.comment;
import cn.edu.xmut.learningplatform.model.discussion;
import cn.edu.xmut.learningplatform.service.commentService;
import cn.edu.xmut.learningplatform.service.discussionService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/comment")
public class commentController {
    @Autowired
    private commentService commentService;
    //发布评论
    @PostMapping("/addComment")
    public ResultUtil<String> addComment(@RequestBody comment comment) {
        commentService.addComment(comment);
        return ResultUtil.success();
    }

    //展开回复
    @PostMapping("/expandComment")
    public ResultUtil<ArrayList<comment>> expandComment(@RequestBody comment comment) {
        Set<Integer> queriedCommentIds = new HashSet<>();
        return ResultUtil.success(commentService.expandComment(comment.getId(), queriedCommentIds));
    }

    //删除回复
    @PostMapping("/deleteComment")
    public ResultUtil<String> deleteComment(@RequestBody comment comment) {

        commentService.deleteComment(comment);
        return ResultUtil.success();
    }

    //修改回复
    @PostMapping("/updateComment")
    public ResultUtil<String> updateComment(@RequestBody comment comment) {
        commentService.updateComment(comment);
        return ResultUtil.success();
    }

}
