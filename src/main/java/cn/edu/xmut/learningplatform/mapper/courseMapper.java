package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface courseMapper {
    List<course>  getAllCourse(course course);

    List<course> getTeacherAllCourse(course course);
}
