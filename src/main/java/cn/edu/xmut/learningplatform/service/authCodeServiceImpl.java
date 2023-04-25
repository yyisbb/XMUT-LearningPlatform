package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.Check;
import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.authCode;
import cn.edu.xmut.learningplatform.mapper.authCodeMapper;
import cn.edu.xmut.learningplatform.model.permission;
import cn.edu.xmut.learningplatform.utils.RandomStringUtil;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class authCodeServiceImpl implements authCodeService {
    @Autowired
    private authCodeMapper authCodeMapper;

    @Override
    public PageInfo<authCode> getAllAuthCode(authCode authCode) {
        PageHelper.startPage(authCode.getCurrent(), authCode.getPageSize());
        List<authCode> allAuthCode = authCodeMapper.getAllAuthCode(authCode);
        return new PageInfo<>(allAuthCode);
    }

    @Override
    public authCode getAuthCodeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new GlobalException(ErrorCode.AUTH_CODE_EMPTY_ERROR);
        }
        return authCodeMapper.getAuthCodeByCode(code);
    }

    @Override
    public void updateAuthCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new GlobalException(ErrorCode.AUTH_CODE_EMPTY_ERROR);
        }

        authCode authCode = authCodeMapper.getAuthCodeByCode(code);

        if (authCode == null) {
            throw new GlobalException(ErrorCode.AUTH_CODE_ERROR);
        }

        if (authCodeMapper.updateAuthCode(authCode) == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }

    }

    @Override
    public void generateAuthCode(authCode authCode) {
        if (StringUtils.isEmpty(authCode.getSchool()) || authCode.getCount() <= 0) {
            throw new GlobalException(ErrorCode.AUTH_CODE_SCHOOL_COUNT_EMPTY_ERROR);
        }

        //校验合法性
        boolean schoolMatches = authCode.getSchool().matches(Check.NAME_REGEX);
        if (!schoolMatches) {
            throw new GlobalException(ErrorCode.SCHOOL_FORMAT_ERROR);
        }
        List<authCode> authCodeList = new LinkedList<>();
        for (int i = 0; i < authCode.getCount(); i++) {
            authCodeList.add(new authCode(null, RandomStringUtil.generateRandomString(8), authCode.getSchool(), null, null, null, null, null, null));
        }

        authCodeMapper.generateAuthCode(authCodeList);

    }

}
