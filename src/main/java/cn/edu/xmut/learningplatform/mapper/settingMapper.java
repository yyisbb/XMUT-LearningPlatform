package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.setting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface settingMapper {
    void addBanner(setting setting);

    void deleteBannerById(Integer id);

    List<setting> getAllBanners();

    void updateBanner(setting setting);

}
