package cn.edu.xmut.learningplatform.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description user_department
 * @author zwj
 * @date 2023-04-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * department_id
     */
    private Integer departmentId;

    /**
     * user_id
     */
    private Integer userId;

}