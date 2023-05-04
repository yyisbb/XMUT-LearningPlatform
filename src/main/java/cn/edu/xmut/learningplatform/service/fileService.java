package cn.edu.xmut.learningplatform.service;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

public interface fileService {
    String uploadFile(MultipartFile file);

}
