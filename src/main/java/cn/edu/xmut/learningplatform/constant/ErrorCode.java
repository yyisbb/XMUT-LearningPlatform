package cn.edu.xmut.learningplatform.constant;


public enum ErrorCode {
    //其他失败
    FAILED("失败"),
    //成功200
    SUCCESS( "成功"),
    //失败500开始
    USERNAME_OR_PASSWORD_ERROR( "用户名或密码错误"),
    ACCOUNT_STATUS_ERROR( "账户已被禁用"),
    PARAMETER_EMPTY_ERROR("参数不能为空"),
    TOKEN_USER_NOT_FOUND_ERROR( "TOKEN校验失败,用户不存在"),
    USER_EMPTY_ERROR("用户不存在"),
    PERMISSION_DENIED( "权限不足,拒绝访问"),
    TOKEN_NULL_ERROR("TOKEN校验失败,Token为空或不存在"),
    PASSWORD_FORMAT_ERROR( "密码格式错误，密码必须是包含大写字母、小写字母、数字、特殊符号（不是字母，数字，下划线，汉字的字符）的6位以上组合。"),
    EMAIL_FORMAT_ERROR( "邮箱格式错误"),
    NAME_FORMAT_ERROR( "姓名格式错误，姓名必须是纯中文。"),
    USERNAME_FORMAT_ERROR("用户名格式错误，用户名必须是包含大写字母、小写字母、数字、中文、特殊符号（不是字母，数字，下划线，汉字的字符）的6位以上组合。"),
    STUDENT_ID_FORMAT_ERROR("学号/职工号格式错误，必须是纯数字的10位组合。"),
    USER_EXIST_ERROR( "用户已存在,请更换学号或用户名"),
    REGISTER_ERROR("注册失败"),
    ROLE_NAME_FORMAT_ERROR( "角色名格式错误，角色名必须是纯中文。"),
    PERMISSION_NAME_FORMAT_ERROR( "权限名格式错误，权限名必须是纯中文。"),
    PERMISSION_METHOD_FORMAT_ERROR( "HTTP请求方法格式错误，必须符合HTTP请求方法"),
    PERMISSION_URL_FORMAT_ERROR( "URL格式错误"),
    ROLE_EXIST_ERROR( "角色已存在,请更换角色SN/ID"),
    ROLE_NOT_EXIST_ERROR( "角色不存在,请更换角色SN/ID"),
    USER_NOT_EXIST_ERROR( "用户不存在"),
    PERMISSION_NOT_EXIST_ERROR( "权限不存在"),
    PERMISSION_EXIST_ERROR( "权限已存在"),
    ROLE_EXIST_PERMISSION_ERROR( "角色存在权限或用户关联,无法删除"),
    PERMISSION_EXIST_PERMISSION_ERROR( "权限存在角色关联,无法删除"),
    AUTH_CODE_EMPTY_ERROR("参数有误,授权码为空"),
    AUTH_CODE_SCHOOL_COUNT_EMPTY_ERROR("参数有误,授权码院校名或数量有误"),
    AUTH_CODE_ERROR("授权码不存在||错误||已使用"),
    SCHOOL_FORMAT_ERROR( "院校名格式错误，院校名必须是纯中文。"),
    SQL_ERROR("SQL有误"),
    UPLOAD_ERROR("文件上传失败"),
    GET_FILE_ERROR("获取文件失败"),
    COURSE_EMPTY_ERROR("课程不存在"),
    USER_COURSE_EXIST_ERROR("学生已加入过该课程"),
    CHAPTER_EMPTY_ERROR("章节不存在"),
    CHAPTER_BELONG_COURSE_EMPTY_ERROR("章节所属课程不匹配"),
    TASK_EMPTY_ERROR("任务点不存在"),
    TASK_PROGRESS_EMPTY_ERROR("任务点进度不存在"),
    GROUP_EMPTY_ERROR("课程组不存在"),
    PREVIEW_EXIST_ERROR("预习已存在,无法添加新预习"),
    TASK_NOT_EMPTY_EMPTY_ERROR("章节存在任务点,无法删除"),
    STUDENT_HAS_JOINED_THE_COURSE("该学生已加入课程"),
    STUDENT_NOT_JOINED_THE_COURSE("该学生未加入课程"),
    PREVIEW_EMPTY_ERROR("预习点不存在"),
    TEACHER_ERROR("任课老师有误"),
    SIGN_CODE_ERROR("签到码有误或签到码已过期"),
    SIGN_EMPTY_ERROR("签到不存在"),
    WORK_EMPTY_ERROR("作业不存在"),
    SIGN_TIME_EXPIRE_ERROR("签到时间已过"),
    SIGN_REPETITION_ERROR("无法重复签到"),
    SCORE_EMPTY_ERROR("成绩不能为空"),
    WORKUP_EMPTY_ERROR("作业不能为空"),




    Discussion_EMPTY_ERROR("该课程没有讨论"),




    PLEASE_DO_NOT_REPEATEDLY_LIKE_ERROR("請勿反復點讚"),
    ;

    private final String message;

    private ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}

