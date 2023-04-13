package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.Check;
import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.role;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.mapper.userMapper;
import cn.edu.xmut.learningplatform.model.userRole;
import cn.edu.xmut.learningplatform.utils.JwtUtils;
import cn.edu.xmut.learningplatform.utils.PropertiesUtil;
import cn.edu.xmut.learningplatform.utils.SecuritySM4;
import cn.edu.xmut.learningplatform.utils.StudentIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userMapper userMapper;


    @Override
    public String login(user user) {
        //校验参数
        if (user.getUsername().length() == 0 || user.getPassword().length() == 0) {
            throw new GlobalException(ErrorCode.USERNAME_OR_PASSWORD_EMPTY_ERROR);
        }

        user.setPassword(SecuritySM4.EncryptStr(user.getPassword(), PropertiesUtil.getSm4Secret()));
        //校验通过验证账号密码
        user sqlUser = userMapper.login(user);
        if (sqlUser == null) {
            throw new GlobalException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
        }

        return JwtUtils.createToken(user);
    }

    @Override
    public user getUserByUserName(String username) {
        return userMapper.getUserInfoByUserName(username);
    }

    @Override
    public void register(user user) {
        //校验参数
        if (user.getUsername().length() == 0
                || user.getPassword().length() == 0 ||
                user.getEmail().length() == 0 ||
                user.getName().length() == 0
        ) {
            throw new GlobalException(ErrorCode.USERNAME_OR_PASSWORD_EMPTY_ERROR);
        }


        //校验密码合法性
        boolean pwdMatches = user.getPassword().matches(Check.PASSWORD_REGEX);
        boolean emailMatches = user.getEmail().matches(Check.EMAIL_REGEX);
        boolean nameMatches = user.getName().matches(Check.NAME_REGEX);
        boolean usernameMatches = user.getUsername().matches(Check.USERNAME_REGEX);
        if (!pwdMatches) {
            throw new GlobalException(ErrorCode.PASSWORD_FORMAT_ERROR);
        }

        if (!emailMatches) {
            throw new GlobalException(ErrorCode.EMAIL_FORMAT_ERROR);
        }
        if (!nameMatches) {
            throw new GlobalException(ErrorCode.NAME_FORMAT_ERROR);
        }
        if (!usernameMatches) {
            throw new GlobalException(ErrorCode.USERNAME_FORMAT_ERROR);
        }

        //将加密后的子串作为密码
        user.setPassword(SecuritySM4.EncryptStr(user.getPassword(), PropertiesUtil.getSm4Secret()));
        user.setCreateTime(new Date());

        //生成学号
        user.setStudentId(StudentIdUtil.generateNumberWithPrefix("STU",8));

        //查重
        user dbUser = userMapper.getUserInfoByUserName(user.getUsername());
        if (dbUser != null ) {
            throw new GlobalException(ErrorCode.USER_EXIST_ERROR);
        }

        //注册失败
        userMapper.register(user);
        if (user.getId() == 0) {
            throw new GlobalException(ErrorCode.REGISTER_ERROR);
        }
    }

    @Override
    public List<userRole> getUserRole(int userid) {
        return userMapper.getUserRole(userid);
    }

}
