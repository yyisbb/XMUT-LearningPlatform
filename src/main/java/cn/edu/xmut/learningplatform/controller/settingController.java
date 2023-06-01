package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.setting;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.xmut.learningplatform.service.settingService;

import java.util.List;

@RestController
@RequestMapping("/setting")
public class settingController {
        @Autowired
        private settingService settingService;

        @Autowired
        public settingController(settingService settingService) {
        this.settingService = settingService;
        }
        @PostMapping("/addBanner")
        public ResultUtil<String> addBanner(@RequestBody setting setting){
            settingService.addBanner(setting);
            return ResultUtil.success();
        }

        @PostMapping("/deleteBannerById")
        public ResultUtil<String> deleteBannerById(@RequestBody setting setting){
                settingService.deleteBannerById(setting.getId());
                return ResultUtil.success();
        }

        @PostMapping("/getAllBanners")
        public ResultUtil<List<setting>> getAllBanners(){

                return ResultUtil.success(settingService.getAllBanners());
        }

        @PostMapping("/updateBanner")
        public ResultUtil<String> updateBanner(@RequestBody setting setting){
                settingService.updateBanner(setting);
                return ResultUtil.success();
        }
}
