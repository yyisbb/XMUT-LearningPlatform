package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.authCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface authCodeMapper {
    /**
     * 获取所有授权码
     * @return
     */
    List<authCode> getAllAuthCode(authCode authCode);

    /**
     * 获取指定授权码
     * @return
     */
    authCode getAuthCodeByCode(String code);

    int updateAuthCode(authCode authCode);

    void generateAuthCode(List<authCode> authCodeList);
}
