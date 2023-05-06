package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.works;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.service.workService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李大大
 * @version 1.0
 */
@RestController
@RequestMapping("/homework")
public class workController {
    @Autowired
    private workService workService;

    /**
     * 查询学生的全部作业
     */
    @PostMapping("/getStudentAllWork")
    public ResultUtil<PageInfo<works>> getStudentAllWork() {
        user loginUser = UserUtil.getLoginUser();
        return ResultUtil.success(workService.getStudentAllWork(loginUser));
    }

    /**
     * 查询教师已布置的作业
     */
    @PostMapping("/getTeacherAllWork")
    public ResultUtil<PageInfo<works>> getTeacherAllWork(){
        user loginUser = UserUtil.getLoginUser();
        return ResultUtil.success(workService.getTeacherAllWork(loginUser));
    }

    /**
     * 教师布置作业
     */
    @PostMapping("/addWork")
    public ResultUtil<String> addWork(@RequestBody works works ){
        user loginUser = UserUtil.getLoginUser();
        workService.addWork(works,loginUser);
        return ResultUtil.success();
    }
    /**
     * 教师删除作业
     * courseId chapterId userId
     */
    @PostMapping("/delWork")
    public ResultUtil<String> delWork(@RequestBody works works ){
        user loginUser = UserUtil.getLoginUser();
        workService.delWork(loginUser,works);
        return ResultUtil.success();
    }
    /**
     * 教师修改作业
     * id
     */
    @PostMapping("/editWork")
    public ResultUtil<String> editWork(@RequestBody works works ){
        user loginUser = UserUtil.getLoginUser();
        workService.editWork(loginUser,works);
        return ResultUtil.success();
    }
//    /**
//     * 查看作业详情
//     */
//    @PostMapping("/detailsWork")
//    public ResultUtil<String> detailsWork(){
//        user loginUser = UserUtil.getLoginUser();
//        return ResultUtil.success(workService.getdetailsWork(loginUser));
//    }
}
