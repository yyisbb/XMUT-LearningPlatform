package cn.edu.xmut.learningplatform.interceptor;


import cn.edu.xmut.learningplatform.annotation.AuthPass;
import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.AuthException;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.utils.JwtUtils;
import cn.edu.xmut.learningplatform.utils.PropertiesUtil;
import cn.edu.xmut.learningplatform.utils.SecuritySM4;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.edu.xmut.learningplatform.service.userService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    userService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("X-Token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有AuthPass注释，有则跳过认证
        if (method.isAnnotationPresent(AuthPass.class)) {
            AuthPass passToken = method.getAnnotation(AuthPass.class);
            if (passToken.required()) {
                return true;
            }
        } else  {
            // 执行认证
            if (StringUtils.isEmpty(token)) {
                //TOKEN为空
                //响应输出对象
                throw new AuthException(ErrorCode.TOKEN_NULL_ERROR);
            }

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
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        //清除线程变量
        UserUtil.removeUser();
    }
}
