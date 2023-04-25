package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.model.chapter;
import cn.edu.xmut.learningplatform.model.preview;
import cn.edu.xmut.learningplatform.service.chapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapter")
public class chapterController {
    /**
     * 查询课程下的所有章节和各个任务点
     */
    @Autowired
    private chapterService chapterService;
    @PostMapping( "/getCourseAllChapter")
    public ResultUtil<List<chapter>> getCourseAllChapter(@RequestBody chapter chapter){
        return ResultUtil.success(chapterService.getCourseAllChapter(chapter));
    }


    @PostMapping( "/updateChapterPPT")
    public ResultUtil<String> updateChapterPPT(@RequestBody chapter chapter){
        chapterService.addChapterPPT(chapter);
        return ResultUtil.success();
    }


    /**
     * 创建章节
     * @param chapter
     * @return
     */
    @PostMapping( "/createChapter")
    public ResultUtil<String> createChapter(@RequestBody chapter chapter){
        chapterService.createChapter(chapter);
        return ResultUtil.success();
    }


    /**
     * 删除章节
     * @param chapter
     * @return
     */
    @PostMapping( "/deleteChapter")
    public ResultUtil<String> deleteChapter(@RequestBody chapter chapter){
        chapterService.deleteChapter(chapter);
        return ResultUtil.success();
    }

    /**
     * 发布章节预习
     * @param preview
     * @return
     */
    @PostMapping( "/releasePreView")
    public ResultUtil<String> releasePreView(@RequestBody preview preview){
        chapterService.releasePreView(preview);
        return ResultUtil.success();
    }
}
