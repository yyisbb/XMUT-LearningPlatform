package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.dto.courseListDTO;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.dto.courseUserDTO;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.xmut.learningplatform.service.courseService;
import cn.edu.xmut.learningplatform.service.userService;

@RestController
@RequestMapping("/course")
public class courseController {
    @Autowired
    private courseService courseService;
    @Autowired
    private userService userService;
    /**
     * 查询所有课程
     */
    @PostMapping("/getAllCourse")
    public ResultUtil<courseListDTO> getAllCourse(@RequestBody course course) {
        return ResultUtil.success(courseService.getAllCourse(course));
    }



    /**
     * 查询指定课程ID的课程
     */
    @PostMapping("/getCourseByCourseId")
    public ResultUtil<course> getCourseByCourseId(@RequestBody course course) {
        return ResultUtil.success(courseService.getCourseByCourseId(course));
    }

    /**
     * 查询指定老师的课程
     */
    @PostMapping("/getTeacherAllCourse")
    public ResultUtil<courseUserDTO> getTeacherAllCourse(@RequestBody course course) {
        courseUserDTO userDTO = new courseUserDTO();
        course.setUserId(UserUtil.getLoginUser().getId());
        userDTO.setCourseListDTO(courseService.getTeacherAllCourse(course));
        userDTO.setUser(userService.getUserByUserId(course.getUserId()));
        return ResultUtil.success(userDTO);
    }


    /**
     * 添加课程
     */
    @PostMapping("/addCourse")
    public ResultUtil<String> addCourse(@RequestBody course course) {
        courseService.addCourse(course);
        return ResultUtil.success();
    }

    /**
     * 查询指定学生选择的课程
     */
    @PostMapping("/selectStudentCourse")
    public ResultUtil<PageInfo<course>> selectStudentCourse(@RequestBody course course){
        return ResultUtil.success(courseService.selectStudentCourse(course));

    }

}
