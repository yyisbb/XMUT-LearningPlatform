package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.settingMapper;
import cn.edu.xmut.learningplatform.model.setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class settingServiceImpl implements settingService{
    @Autowired
    private settingMapper settingMapper;

    @Autowired
    public settingServiceImpl(settingMapper settingMapper){
        this.settingMapper = settingMapper;
    }
    @Override
    public void addBanner(setting setting) {
        //参数空
        if (ObjectUtils.isEmpty(setting)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        settingMapper.addBanner(setting);
    }

    @Override
    public void deleteBannerById(Integer id) {
        if (id == null || id == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        settingMapper.deleteBannerById(id);
    }

    @Override
    public List<setting> getAllBanners() {
        return settingMapper.getAllBanners();
    }

    @Override
    public void updateBanner(setting setting) {
        //参数空
        if (ObjectUtils.isEmpty(setting)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        settingMapper.updateBanner(setting);
    }
}
