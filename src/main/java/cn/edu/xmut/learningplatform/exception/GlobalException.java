package cn.edu.xmut.learningplatform.exception;


import cn.edu.xmut.learningplatform.constant.ErrorCode;

/**
 * 其他异常返回500 并给出报错信息
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误信息
     */
    protected String errorMsg;

    public GlobalException() {
        super();
    }

    public GlobalException(ErrorCode error) {
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

