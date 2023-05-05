package cn.edu.xmut.learningplatform.dto;

import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.user;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class courseUserDTO {
    private courseListDTO courseListDTO;
    private user user;
}
