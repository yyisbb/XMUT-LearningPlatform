package cn.edu.xmut.learningplatform.utils;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import lombok.Data;

@Data
public class ResultUtil<T> {

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    private ResultUtil() {
    }

    public static <T> ResultUtil<T> success() {
        ResultUtil<T> resultUtil = new ResultUtil<>();
        resultUtil.setData(null);
        return resultUtil;
    }

    public static <T> ResultUtil<T> success(T data) {
        ResultUtil<T> resultUtil = success();
        resultUtil.setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> fail(String message) {
        ResultUtil<T> resultUtil = new ResultUtil<>();
        resultUtil.setMsg(message);
        return resultUtil;
    }


}
