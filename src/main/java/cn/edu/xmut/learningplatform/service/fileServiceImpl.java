package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.utils.UploadFileUtil;
import com.alibaba.druid.util.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

@Service
public class fileServiceImpl implements fileService{
    @Override
    public String uploadFile(MultipartFile file) {
        return UploadFileUtil.uploadFile(file);
    }
}
