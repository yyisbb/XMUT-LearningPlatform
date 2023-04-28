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

    void addCourse(course course);


    /**
     * 查询指定课程ID的课程
     */
    course getCourseByCourseId(course course);

    /**
     * 查询指定学生ID的课程
     */
    PageInfo<course>selectStudentCourse(course course) ;

}
