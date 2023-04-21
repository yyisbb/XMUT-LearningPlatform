package cn.edu.xmut.learningplatform.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class rolePermission {
    private String[] permissionIds;
    private Integer roleId;
}
