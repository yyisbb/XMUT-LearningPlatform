package cn.edu.xmut.learningplatform.utils;


import cn.edu.xmut.learningplatform.model.user;

public class UserUtil {
    /**
     * 线程变量，存放user实体类信息，即使是静态的与其他线程也是隔离的
     */
    private static final ThreadLocal<user> userThreadLocal = new ThreadLocal<>();

    /**
     * 获取当前登录用户
     * @return
     */
    public static user getLoginUser() {
        return userThreadLocal.get();
    }

    public static void setLoginUser(user user) {
        userThreadLocal.set(user);
    }

    public static void removeUser(){
        userThreadLocal.remove();
    }
}
