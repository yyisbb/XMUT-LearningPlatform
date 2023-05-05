package cn.edu.xmut.learningplatform.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    public static Date[] addMinutesToCurrentTime(int minutes) {
        Calendar calendar = Calendar.getInstance();
        Date currentTime = new Date();
        calendar.setTime(currentTime);
        calendar.add(Calendar.MINUTE, minutes);
        Date addedTime = calendar.getTime();
        return new Date[] { currentTime, addedTime };
    }
}
