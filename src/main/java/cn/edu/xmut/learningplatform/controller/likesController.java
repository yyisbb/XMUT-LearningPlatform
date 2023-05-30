package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.discussion;
import cn.edu.xmut.learningplatform.model.likes;
import cn.edu.xmut.learningplatform.service.discussionService;
import cn.edu.xmut.learningplatform.service.likesService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/likes")
public class likesController {
    @Autowired
    private likesService likesService;
    //点赞文章
    @PostMapping("/likesDiscussion")
    public ResultUtil<String> likesDiscussion(@RequestBody discussion discussion) {
        likesService.likesDiscussion(discussion);
        return ResultUtil.success();
    }
    //取消点赞
    @PostMapping("/cancelLikes")
    public ResultUtil<String> cancelLikes(@RequestBody discussion discussion) {
        likesService.cancelLikes(discussion);
        return ResultUtil.success();
    }
    //查询点赞数为前limit的讨论
    @PostMapping("/selectDiscussion")
    public ResultUtil<ArrayList<discussion>> selectDiscussion() {
        ArrayList<discussion> discussions = likesService.selectDiscussion();
        return ResultUtil.success(discussions);
    }

    //查詢該用戶喜歡的所有討論
    @PostMapping("/selectLikesDiscussion")
    public ResultUtil<ArrayList<discussion>> selectLikesDiscussion() {
        ArrayList<discussion> discussions = likesService.selectLikesDiscussion();
        return ResultUtil.success(discussions);
    }
}
