package cn.edu.xmut.learningplatform.exception;


import cn.edu.xmut.learningplatform.constant.ErrorCode;

/**
 * 授权异常返回401状态码
 */
public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误信息
     */
    protected String errorMsg;

    public TokenException() {
        super();
    }

    public TokenException(ErrorCode error) {
        super(error.getMessage());
        this.errorMsg = error.getMessage();
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }



    public String getErrorMsg() {
        return errorMsg;
    }
}

