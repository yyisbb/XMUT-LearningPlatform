package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.mapper.authCodeMapper;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class courseServiceImpl implements courseService {
    @Autowired
    private courseMapper courseMapper;
    @Override
    public PageInfo<course> getAllCourse(course course) {
        if (course.getCurrent()!=0&&course.getPageSize()!=0){
            PageHelper.startPage(course.getCurrent(), course.getPageSize());
        }
        List<course> allAuthCode = courseMapper.getAllCourse(course);
        return new PageInfo<>(allAuthCode);
    }

    @Override
    public PageInfo<course> getTeacherAllCourse(course course) {
        if (course.getCurrent()!=0&&course.getPageSize()!=0){
            PageHelper.startPage(course.getCurrent(), course.getPageSize());
        }

        List<course> allAuthCode = courseMapper.getTeacherAllCourse(course);
        return new PageInfo<>(allAuthCode);
    }
}
