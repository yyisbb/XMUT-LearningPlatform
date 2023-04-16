package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.authCode;
import cn.edu.xmut.learningplatform.mapper.authCodeMapper;
import cn.edu.xmut.learningplatform.model.permission;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class authCodeServiceImpl implements authCodeService{
    @Autowired
    private authCodeMapper authCodeMapper;
    @Override
    public PageInfo<authCode> getAllAuthCode(authCode authCode) {
        PageHelper.startPage(authCode.getCurrent(), authCode.getPageSize());
        List<authCode> allAuthCode =  authCodeMapper.getAllAuthCode();
        return new PageInfo<>(allAuthCode);
    }

    @Override
    public authCode getAuthCodeByCode(String code) {
        if (StringUtils.isEmpty(code)){
            throw new GlobalException(ErrorCode.AUTH_CODE_EMPTY_ERROR);
        }
        return authCodeMapper.getAuthCodeByCode(code);
    }

}
