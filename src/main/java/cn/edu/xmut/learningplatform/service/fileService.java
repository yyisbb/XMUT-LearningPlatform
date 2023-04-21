package cn.edu.xmut.learningplatform.service;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface fileService {
    String uploadFile(MultipartFile file);
}
