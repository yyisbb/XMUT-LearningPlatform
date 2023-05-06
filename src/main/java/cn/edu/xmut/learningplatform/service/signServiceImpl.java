package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.constant.RedisKey;
import cn.edu.xmut.learningplatform.constant.SignType;
import cn.edu.xmut.learningplatform.mapper.signMapper;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.sign;
import cn.edu.xmut.learningplatform.model.signUser;
import cn.edu.xmut.learningplatform.model.userCourse;
import cn.edu.xmut.learningplatform.utils.RandomStringUtil;
import cn.edu.xmut.learningplatform.utils.RedisUtil;
import cn.edu.xmut.learningplatform.utils.TimeUtils;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

        //生成签到码
        String signCode = RandomStringUtil.generateRandomNumberString(4);
        sign sqlSign = new sign(sign.getCourseId(), startTime, endTime, UserUtil.getLoginUser().getId(), signCode);
        //创建签到
        signMapper.createSign(sqlSign);
        if (sqlSign.getId() == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
        //发布到Redis
        redisUtil.setEx(signCode, RedisKey.SIGN + sqlSign.getId(), TimeUnit.MINUTES.toSeconds(sign.getDuration()));
    }

    @Override
    public PageInfo<sign> getSignListByCourseId(sign sign) {
        if (sign.getCourseId() == null || sign.getCourseId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if (sign.getCurrent()!=0&&sign.getPageSize()!=0){
            PageHelper.startPage(sign.getCurrent(), sign.getPageSize());
        }

        return new PageInfo<>(signMapper.getSignListByCourseId(sign.getCourseId()));
    }

    @Override
    public void signIn(String signCode) {
        //参数判空
        if (StringUtils.isEmpty(signCode)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //去缓存查
        String signIdValue = redisUtil.get(signCode);
        if (StringUtils.isEmpty(signIdValue)) {
            throw new GlobalException(ErrorCode.SIGN_CODE_ERROR);
        }

        //去除前缀
        String signId = signIdValue.replaceFirst(RedisKey.SIGN, "");

        //查询指定签到
        sign sqlSign = signMapper.getSignBySignId(Integer.valueOf(signId));
        if (ObjectUtils.isEmpty(sqlSign) || sqlSign.getId() == 0) {
            throw new GlobalException(ErrorCode.SIGN_EMPTY_ERROR);
        }

        //存在 判断是不是自己的上的课程
        course sqlCourse = courseMapper.getCourseByCourseId(sqlSign.getCourseId());
        if (ObjectUtils.isEmpty(sqlCourse) || sqlCourse.getId() == 0) {
            throw new GlobalException(ErrorCode.COURSE_EMPTY_ERROR);
        }

        //看看是不是自己所在的课程
        List<userCourse> userCourses = courseMapper.selectByCourseIdOrUserId(UserUtil.getLoginUser().getId(), sqlCourse.getId());
        if (userCourses == null || userCourses.size() == 0) {
            throw new GlobalException(ErrorCode.STUDENT_NOT_JOINED_THE_COURSE);
        }

        //签到成功
        //查询签到记录 无法重复签到
        signUser sqlSignRecord = signMapper.getSignRecord(sqlSign.getId(), UserUtil.getLoginUser().getId());
        if (!ObjectUtils.isEmpty(sqlSignRecord)) {
            throw new GlobalException(ErrorCode.SIGN_REPETITION_ERROR);
        }

        //查询签到是否过期
        if (sqlSign.getEndTime().compareTo(new Date()) < 0) {
            throw new GlobalException(ErrorCode.SIGN_TIME_EXPIRE_ERROR);
        }

        //插入记录
        int count = signMapper.signIn(new signUser(sqlSign.getId(), UserUtil.getLoginUser().getId(), null, null, SignType.SUCCESS.getStatus()));
        if (count == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public sign getSignBySignId(Integer signId) {
        //参数判空
        if (signId == null || signId == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询该签到
        sign sqlSign = signMapper.getSignBySignId(signId);
        if (ObjectUtils.isEmpty(sqlSign) || sqlSign.getId() == 0) {
            throw new GlobalException(ErrorCode.SIGN_EMPTY_ERROR);
        }

        //去缓存查过期时间
        sqlSign.setExpire(redisUtil.getExpire(sqlSign.getSignCode()));

        //查询签到人数
        sqlSign.setSignCount(signMapper.getSignCount(signId));
        //签到所在课程总人数
        sqlSign.setTotal(courseMapper.getChooseCourseCountByCourseId(sqlSign.getCourseId()));
        return sqlSign;
    }


}
