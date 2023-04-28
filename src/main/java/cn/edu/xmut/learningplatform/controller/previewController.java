package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.preview;
import cn.edu.xmut.learningplatform.service.previewService;
import cn.edu.xmut.learningplatform.service.userService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
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
     * @param course
     * @return
     */
    @PostMapping("/selectByIdPreview")
    public ResultUtil<PageInfo<preview>> selectByIdPreview(@RequestBody course course){
        return ResultUtil.success(previewService.selectByIdPreview(course));
    }
}
