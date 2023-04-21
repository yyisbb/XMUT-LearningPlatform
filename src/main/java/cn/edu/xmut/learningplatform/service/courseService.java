package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.model.authCode;
import cn.edu.xmut.learningplatform.model.course;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface courseService {
    PageInfo<course>  getAllCourse(course course);

    PageInfo<course> getTeacherAllCourse(course course);
}
