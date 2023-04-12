package cn.edu.xmut.learningplatform.constant;


public enum ErrorCode {
    //其他失败
    FAILED("失败"),
    //成功200
    SUCCESS( "成功"),
    //失败500开始
    USERNAME_OR_PASSWORD_ERROR( "用户名或密码错误"),
    USERNAME_OR_PASSWORD_EMPTY_ERROR("用户名或密码为空"),
    TOKEN_USER_NOT_FOUND_ERROR( "TOKEN校验失败,用户不存在"),
    TOKEN_NULL_ERROR("TOKEN校验失败,Token为空或不存在"),
    PASSWORD_FORMAT_ERROR( "密码格式错误，密码必须是包含大写字母、小写字母、数字、特殊符号（不是字母，数字，下划线，汉字的字符）的6位以上组合。"),
    EMAIL_FORMAT_ERROR( "邮箱格式错误"),
    NAME_FORMAT_ERROR( "姓名格式错误，用户名必须是纯中文。"),
    USERNAME_FORMAT_ERROR("用户名格式错误，用户名必须是包含大写字母、小写字母、数字、中文、特殊符号（不是字母，数字，下划线，汉字的字符）的6位以上组合。"),
    USER_EXIST_ERROR( "用户已存在,请更换学号或用户名"),
    REGISTER_ERROR("注册失败"),
    ;

    private final String message;

    private ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}

