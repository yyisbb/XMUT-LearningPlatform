package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.service.fileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class fileController {
    @Autowired
    private fileService fileService;
    @PostMapping(value = "/uploadFile",consumes = "multipart/form-data")
    public ResultUtil<String> uploadFile(@RequestParam("file") MultipartFile multipartFile){
        String fileUrl = fileService.uploadFile(multipartFile);
        return ResultUtil.success(fileUrl);
    }
}
