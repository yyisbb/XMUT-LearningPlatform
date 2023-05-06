package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.authCode;
import cn.edu.xmut.learningplatform.model.sign;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.vo.signInVo;
import com.github.pagehelper.PageInfo;
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


    /**
     * 根据签到ID获取签到信息
      * @param signInVo
     * @return
     */
    @PostMapping("/getSignBySignId")
    public ResultUtil<sign> getSignBySignId(@RequestBody signInVo signInVo) {
        return ResultUtil.success(signService.getSignBySignId(signInVo.getSignId()));
    }


    /**
     * 根据课程ID获取该课程的签到列表
     * @param sign
     * @return
     */
    @PostMapping("/getSignListByCourseId")
    public ResultUtil<PageInfo<sign> > getSignListByCourseId(@RequestBody sign sign) {
        return ResultUtil.success(signService.getSignListByCourseId(sign));
    }

    /**
     * 签到
     * @param signInVo
     * @return
     */
    @PostMapping("/signIn")
    public ResultUtil<String> signIn(@RequestBody signInVo signInVo) {
        signService.signIn(signInVo.getSignCode());
        return ResultUtil.success();
    }


}
