package cn.edu.xmut.learningplatform.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description user_role
 * @author zwj
 * @date 2023-04-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * user_id
     */
    private Integer userId;

    /**
     * role_id
     */
    private Integer roleId;

}