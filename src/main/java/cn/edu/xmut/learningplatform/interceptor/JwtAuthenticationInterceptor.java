package cn.edu.xmut.learningplatform.interceptor;


import cn.edu.xmut.learningplatform.annotation.AuthPass;
import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.AuthException;
import cn.edu.xmut.learningplatform.model.*;
import cn.edu.xmut.learningplatform.utils.JwtUtils;
import cn.edu.xmut.learningplatform.utils.PropertiesUtil;
import cn.edu.xmut.learningplatform.utils.SecuritySM4;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import cn.edu.xmut.learningplatform.service.roleService;
import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.edu.xmut.learningplatform.service.userService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    userService userService;
    @Autowired
    roleService roleService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("X-Token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        //做RBAC权限校验
        //1.拿到请求地址
        String requestURI = httpServletRequest.getRequestURI();
        String requestMethod = httpServletRequest.getMethod();

        //检查是否有AuthPass注释，有则跳过认证
        if (method.isAnnotationPresent(AuthPass.class)) {
            AuthPass passToken = method.getAnnotation(AuthPass.class);
            if (passToken.required()) {
                return true;
            }
        } else  {
            // 执行认证  //Token为空 不允许通过
            if (StringUtils.isEmpty(token)) {
                //TOKEN为空
                //响应输出对象
                throw new AuthException(ErrorCode.TOKEN_NULL_ERROR);
            }

            //校验是否是正确的用户 非伪造的

            // 获取 token 中的 username
            String encryptionUsername = JwtUtils.getAudience(token);

            // 验证 token
            JwtUtils.verifyToken(token, encryptionUsername);

            //检查用户是否存在
            user user = userService.getUserByUserName(SecuritySM4.DecryptStr(encryptionUsername, PropertiesUtil.getSm4Secret()));
            if (user == null || user.getId() == 0) {
                //用户不存在
                throw new AuthException(ErrorCode.TOKEN_USER_NOT_FOUND_ERROR);
            }

            //验证成功
            UserUtil.setLoginUser(user);


            //2.开始鉴权RBAC
            //(1) 查询该用户的角色列表
            List<userRole> roleList = userService.getUserRole(user.getId());

            //管理员跳过
            for (userRole userRole : roleList) {
                if ("admin".equals(userRole.getRoleSn())){
                    return true;
                }
            }

            //TODO 这里要做Redis缓存所有的角色对应的权限 后面写....
            //(2) 查询该角色列表对应的权限集合
            ArrayList<rolePermission> allowPermissionList = new ArrayList<>();
            for (userRole role : roleList) {
                allowPermissionList.addAll(roleService.getRolePermissionList(role.getRoleId()));
            }

            //如果权限为空或者没有存在该请求的path和method就拒绝执行 抛出异常
            if (allowPermissionList.size()==0){
                throw new AuthException(ErrorCode.PERMISSION_DENIED);
            }

            for (rolePermission permission : allowPermissionList) {
                if (permission.getRequestUrl().equals(requestURI)
                    &&
                        permission.getRequestMethod().equals(requestMethod)
                ){
                    return true;
                }
            }

            throw new AuthException(ErrorCode.PERMISSION_DENIED);
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
        //TODO 做日志记录
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        //清除线程变量
        UserUtil.removeUser();
    }
}
