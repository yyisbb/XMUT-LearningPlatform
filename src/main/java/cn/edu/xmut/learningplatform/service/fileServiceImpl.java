package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.utils.UploadFileUtil;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class fileServiceImpl implements fileService{
    @Override
    public String uploadFile(MultipartFile file) {
        return UploadFileUtil.uploadFile(file);
    }
}
