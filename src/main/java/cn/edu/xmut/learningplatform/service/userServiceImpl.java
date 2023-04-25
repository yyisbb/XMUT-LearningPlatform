package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.Check;
import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.constant.RoleType;
import cn.edu.xmut.learningplatform.constant.Status;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.authCode;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.mapper.userMapper;
import cn.edu.xmut.learningplatform.model.userRole;
import cn.edu.xmut.learningplatform.utils.JwtUtils;
import cn.edu.xmut.learningplatform.utils.PropertiesUtil;
import cn.edu.xmut.learningplatform.utils.SecuritySM4;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userMapper userMapper;
    @Autowired
    private authCodeService  authCodeService;


    /**
     * 事务
     */
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String login(user user) {
        //校验参数
        if (user.getUsername().length() == 0 || user.getPassword().length() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        user.setPassword(SecuritySM4.EncryptStr(user.getPassword(), PropertiesUtil.getSm4Secret()));
        //校验通过验证账号密码
        user sqlUser = userMapper.login(user);
        if (sqlUser == null) {
            throw new GlobalException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
        }

        //账户禁用
        if (sqlUser.getStatus()==Status.DISABLE.getStatus()){
            throw new GlobalException(ErrorCode.ACCOUNT_STATUS_ERROR);
        }

        return JwtUtils.createToken(user);
    }

    @Override
    public user getUserByUserName(String username) {
        user user = userMapper.getUserInfoByUserName(username);
        if (user==null||user.getId()==0){
            throw new GlobalException(ErrorCode.USER_EMPTY_ERROR);
        }
        //查询角色
        userRole role = userMapper.getUserRole(user.getId());
        user.setAccess(role);
        return user;
    }

    @Override
    public user getUserByUserId(Integer userId) {
        user user = userMapper.getUserInfoByUserId(userId);
        if (user==null||user.getId()==0){
            throw new GlobalException(ErrorCode.USER_EMPTY_ERROR);
        }
        //查询角色
        userRole role = userMapper.getUserRole(user.getId());
        user.setAccess(role);
        return user;
    }

    @Override
    public void register(user user) {
        //校验参数
        if (user.getUsername().length() == 0
                || user.getPassword().length() == 0 ||
                user.getEmail().length() == 0 ||
                user.getName().length() == 0||
                user.getStudentId().length() == 0
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }


        //校验密码合法性
        boolean pwdMatches = user.getPassword().matches(Check.PASSWORD_REGEX);
        boolean emailMatches = user.getEmail().matches(Check.EMAIL_REGEX);
        boolean nameMatches = user.getName().matches(Check.NAME_REGEX);
        boolean usernameMatches = user.getUsername().matches(Check.USERNAME_REGEX);
        boolean studentIdMatches = user.getStudentId().matches(Check.STUDENT_ID__REGEX);
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
        if (!studentIdMatches) {
            throw new GlobalException(ErrorCode.STUDENT_ID_FORMAT_ERROR);
        }

        //将加密后的子串作为密码
        user.setPassword(SecuritySM4.EncryptStr(user.getPassword(), PropertiesUtil.getSm4Secret()));

        //查重
        List<user> dbUser = userMapper.getUserInfoByUserNameOrStuID(user.getUsername(), user.getStudentId());
        if (dbUser != null && dbUser.size()!=0) {
            throw new GlobalException(ErrorCode.USER_EXIST_ERROR);
        }


        //开事务
        transactionTemplate.execute(status -> {
            try {
                //先注册
                userMapper.register(user);
                if (user.getId() == 0) {
                    throw new GlobalException(ErrorCode.REGISTER_ERROR);
                }


                //如果授权码并且存在 就是老师
                String authCode = user.getAuthCode();
                if (!StringUtils.isEmpty(authCode)){
                    authCode code = authCodeService.getAuthCodeByCode(authCode);
                    //授权码为空或被使用了
                    if (code == null ||code.getStatus()== Status.ENABLE.getStatus()) {
                        throw new GlobalException(ErrorCode.AUTH_CODE_ERROR);
                    }
                    //授权码不空且存在
                    userMapper.createUserRole(user.getId(), RoleType.TEACHER.getType());
                    //老师设置院校
                    user.setSchool(code.getSchool());
                    //设置老师状态
                    user.setStatus(Status.ENABLE.getStatus());
                    userMapper.updateUser(user);

                    //用完禁用授权码
                    authCodeService.updateAuthCode(authCode);
                }else {
                    //授权码为空是学生
                    userMapper.createUserRole(user.getId(), RoleType.STUDENT.getType());
                }

            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
            return null;
        });

    }

    @Override
    public userRole getUserRole(int userid) {
        return userMapper.getUserRole(userid);
    }

    @Override
    public PageInfo<user> getAllUser(user user) {
        PageHelper.startPage(user.getCurrent(), user.getPageSize());
        List<user> allUser = userMapper.getAllUser(user);

        for (user value : allUser) {
            userRole userRole = getUserRole(value.getId());
            value.setAccess(userRole);
        }


        // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类

        return new PageInfo<user>(allUser);
    }

    @Override
    public void updateStatus(String username) {
        user user = userMapper.getUserInfoByUserName(username);
        if (ObjectUtils.isEmpty(user)){
            throw new GlobalException(ErrorCode.USER_EMPTY_ERROR);
        }

        if (user.getStatus()==Status.ENABLE.getStatus()){
            user.setStatus(Status.DISABLE.getStatus());
        }else {
            user.setStatus(Status.ENABLE.getStatus());
        }

        userMapper.updateUser(user);
    }

    @Override
    public void insertUserRole(Integer userId, Integer roleId) {
        if (userId==0||roleId==0){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询用户是否存在
        user sqlUser = userMapper.getUserInfoByUserId(userId);
        if (ObjectUtils.isEmpty(sqlUser)) {
            throw new GlobalException(ErrorCode.USER_NOT_EXIST_ERROR);
        }

        //开事务
        transactionTemplate.execute(status -> {
            try {
                //删除用户角色
                userMapper.deleteUserRoleByUserId(userId);
                userMapper.updateUser(sqlUser);
                userMapper.createUserRole(userId,roleId);
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
            return null;
        });
    }

}
