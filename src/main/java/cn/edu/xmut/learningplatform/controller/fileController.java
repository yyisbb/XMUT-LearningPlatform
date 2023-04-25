package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.service.fileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class fileController {
    @Autowired
    private fileService fileService;

    @ResponseBody
    @PostMapping(value = "/uploadFile",consumes = "multipart/form-data")
    public ResultUtil<String> uploadFile(@RequestParam("file") MultipartFile multipartFile){
        String fileUrl = fileService.uploadFile(multipartFile);
        return ResultUtil.success(fileUrl);
    }







}
