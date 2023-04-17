package cn.edu.xmut.learningplatform.exception;

import cn.edu.xmut.learningplatform.utils.ResultUtil;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice()
public class CustomExceptionHandler<E> {


    /**
     * Token异常都返回401状态码
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = TokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultUtil<String> tokenHandler(HttpServletRequest req, TokenException e) {
        return ResultUtil.fail(e.getMessage());
    }

    /**
     * 权限不足返回403状态码
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = AuthException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultUtil<String> authHandler(HttpServletRequest req, AuthException e) {
        return ResultUtil.fail(e.getMessage());
    }

    /**
     * 其他所有异常都返回500状态码
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultUtil<String> otherHandler(HttpServletRequest req, GlobalException e) {
        return ResultUtil.fail(e.getMessage());
    }




}
