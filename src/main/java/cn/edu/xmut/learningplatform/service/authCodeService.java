package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.authCode;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface authCodeService {
    /**
     * 获取所有授权码
     * @return
     */
    PageInfo<authCode> getAllAuthCode(authCode authCode);


    /**
     * 获取指定授权码
     * @return
     */
    authCode getAuthCodeByCode(String code);

    void updateAuthCode(String code);

    void generateAuthCode(authCode authCode);
}
