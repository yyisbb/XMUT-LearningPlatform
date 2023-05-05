package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.sign;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.xmut.learningplatform.service.signService;

import java.util.List;

@RestController
@RequestMapping("/sign")
public class signController {
    @Autowired
    private signService signService;

    /**
     * 发布签到任务
     * @return
     */
    @PostMapping("/createSign")
    public ResultUtil<String> createSign(@RequestBody sign sign) {
        signService.createSign(sign);
        return ResultUtil.success();
    }


    @PostMapping("/getSignListByCourseId")
    public ResultUtil<List<sign>> getSignListByCourseId(@RequestBody sign sign) {
        return ResultUtil.success(signService.getSignListByCourseId(sign.getCourseId()));
    }


}
