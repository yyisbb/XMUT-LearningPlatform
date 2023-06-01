package cn.edu.xmut.learningplatform.service;


import cn.edu.xmut.learningplatform.model.setting;

import java.util.List;

public interface settingService {
    void addBanner(setting setting);

    void deleteBannerById(Integer id);

    List<setting> getAllBanners();

    void updateBanner(setting setting);
}
