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
        //TODO CodeReview 新增按课程查询
        user loginUser = UserUtil.getLoginUser();
        return ResultUtil.success(workService.getStudentAllWork(loginUser));
    }

    /**
     * 教师布置作业
     */
    @PostMapping("/addWork")
    public ResultUtil<String> addWork(@RequestBody works works) {
        workService.addWork(works);
        return ResultUtil.success();
    }

    /**
     * 教师删除作业
     * courseId chapterId userId
     */
    @PostMapping("/delWork")
    public ResultUtil<String> delWork(@RequestBody works works) {
        workService.delWork( works);
        return ResultUtil.success();
    }

    /**
     * 教师修改作业
     * id
     */
    @PostMapping("/editWork")
    public ResultUtil<String> editWork(@RequestBody works works) {
        workService.editWork( works);
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
