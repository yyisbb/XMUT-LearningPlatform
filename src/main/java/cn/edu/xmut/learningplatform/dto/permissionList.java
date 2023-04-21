package cn.edu.xmut.learningplatform.dto;

import java.util.List;
import cn.edu.xmut.learningplatform.model.permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class permissionList {
    private List<permission> allowPermission;
    private List<permission> notAllowPermission;
}
