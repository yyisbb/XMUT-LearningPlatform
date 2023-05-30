package cn.edu.xmut.learningplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class bannerVo {
    private List<String> bannerImgList;
}
