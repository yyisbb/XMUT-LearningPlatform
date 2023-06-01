package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.dto.courseListDTO;
import cn.edu.xmut.learningplatform.model.authCode;
import cn.edu.xmut.learningplatform.model.course;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface courseService {
    courseListDTO getAllCourse(course course);

    courseListDTO getTeacherAllCourse(course course);

    void addCourse(course course);


    /**
     * 查询指定课程ID的课程
     */
    course getCourseByCourseId(course course);

    /**
     * 查询指定学生ID的课程
     */
    PageInfo<course>selectStudentCourse(course course) ;

    void studentAddCourse(course course);
}
