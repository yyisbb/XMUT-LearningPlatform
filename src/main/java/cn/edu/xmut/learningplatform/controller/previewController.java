package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.preview;
import cn.edu.xmut.learningplatform.model.taskProgress;
import cn.edu.xmut.learningplatform.service.previewService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preview")
public class previewController {
    @Autowired
    private previewService previewService;

    /**
     * 学生查询预习课程
     *
     * @param course
     * @return
     */
    @PostMapping("/selectPreviewByCourseId")
    public ResultUtil<PageInfo<preview>> selectPreviewByCourseId(@RequestBody course course) {
        return ResultUtil.success(previewService.selectPreviewByCourseId(course));
    }

    /**
     * 修改预习进度
     *
     * @param taskProgress
     * @return
     */
    @PostMapping("/updatePreviewProgress")
    public ResultUtil<String> updatePreviewProgress(@RequestBody taskProgress taskProgress) {
        previewService.updatePreviewProgress(new taskProgress(taskProgress, UserUtil.getLoginUser().getId()));
        return ResultUtil.success();
    }


    /**
     * 查询预习进度
     *
     * @param taskProgress
     * @return
     */
    @PostMapping("/getPreviewProgress")
    public ResultUtil<taskProgress> getPreviewProgress(@RequestBody taskProgress taskProgress) {
        return ResultUtil.success(previewService.getPreviewProgress(new taskProgress(taskProgress, UserUtil.getLoginUser().getId())))
        ;
    }
}
