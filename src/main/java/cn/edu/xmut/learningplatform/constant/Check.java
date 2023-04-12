package cn.edu.xmut.learningplatform.constant;

public class Check {
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,16}$";
    public static final String EMAIL_REGEX = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    public static final String NAME_REGEX = "^[\\u4e00-\\u9fa5]{0,}$";
    public static final String USERNAME_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,16}$";
}
