package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.authCode;
import cn.edu.xmut.learningplatform.service.authCodeService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authCode")
public class authCodeController {
    @Autowired
    private authCodeService authCodeService;


    /**
     * 获取所有授权码
     * @return
     */
    @PostMapping("/getAllAuthCode")
    public ResultUtil<PageInfo<authCode>> getAllAuthCode(@RequestBody  authCode authCode){
        return  ResultUtil.success(authCodeService.getAllAuthCode(authCode));
    }


    /**
     * 获取指定授权码
     * @return
     */
    @PostMapping("/getAuthCode")
    public ResultUtil<authCode> getAuthCode(@RequestBody authCode authCode){
        return  ResultUtil.success(authCodeService.getAuthCodeByCode(authCode.getCode()));
    }
}
