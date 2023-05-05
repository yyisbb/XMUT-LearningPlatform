package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.constant.RedisKey;
import cn.edu.xmut.learningplatform.mapper.signMapper;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.sign;
import cn.edu.xmut.learningplatform.utils.RandomStringUtil;
import cn.edu.xmut.learningplatform.utils.RedisUtil;
import cn.edu.xmut.learningplatform.utils.TimeUtils;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class signServiceImpl implements signService {
    @Autowired
    private signMapper signMapper;
    @Autowired
    private courseMapper courseMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void createSign(sign sign) {
        if (sign.getDuration() == null || sign.getDuration() == 0
                || sign.getCourseId() == null || sign.getCourseId() == 0
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询课程是否存在
        course sqlCourse = courseMapper.getCourseByCourseId(sign.getCourseId());
        if (ObjectUtils.isEmpty(sqlCourse)) {
            throw new GlobalException(ErrorCode.COURSE_EMPTY_ERROR);
        }

        //查询课程任课老师是否与当前老师一致
        if (!sqlCourse.getUserId().equals(UserUtil.getLoginUser().getId())) {
            throw new GlobalException(ErrorCode.TEACHER_ERROR);
        }

        Date[] times = TimeUtils.addMinutesToCurrentTime(sign.getDuration());
        Date startTime = times[0];
        Date endTime = times[1];

        sign sqlSign = new sign(null, sign.getCourseId(), startTime, endTime, UserUtil.getLoginUser().getId(), null);
        //创建签到
        signMapper.createSign(sqlSign);
        if (sqlSign.getId() == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
        //发布到Redis
        redisUtil.setEx(RedisKey.SIGN+sqlSign.getCourseId(), RandomStringUtil.generateRandomString(5), TimeUnit.MINUTES.toSeconds(sign.getDuration()));
    }

    @Override
    public List<sign> getSignListByCourseId(Integer courseId) {
        if (courseId == null || courseId == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        return signMapper.getSignListByCourseId(courseId);
    }


}
