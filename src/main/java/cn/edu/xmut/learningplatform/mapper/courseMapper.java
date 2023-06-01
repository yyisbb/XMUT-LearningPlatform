package cn.edu.xmut.learningplatform.mapper;

import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.model.userCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface courseMapper {
    List<course>  getAllCourse(course course);

    List<course> getTeacherAllCourse(course course);

    void addCourse(course course);


    /**
     * 根据课程ID查指定课程
     * @param courseId
     * @return
     */
    course getCourseByCourseId(Integer courseId);
    course getCourseByCourseCode(String courseCode);

    List<course> getCourseByGroupId(String groupId);

    /**
     * 根据当前id查选课的id
     * @param userId
     * @param courseId
     * @return
     */
    List<userCourse> selectByCourseIdOrUserId(Integer userId, Integer courseId);


    Integer getChooseCourseCountByCourseId(Integer courseId);

    List<user> getUserListByCourseId(Integer courseId);

    int studentAddCourse(userCourse userCourse);

    userCourse getUserCourse(userCourse userCourse);
}
