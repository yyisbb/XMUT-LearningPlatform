package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.userWork;
import cn.edu.xmut.learningplatform.model.works;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.service.workService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import cn.edu.xmut.learningplatform.vo.fileVo;
import cn.edu.xmut.learningplatform.vo.workVo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 按课程查询作业
     */
    @PostMapping("/getCourseAllWork")
    public ResultUtil<PageInfo<works>> getCourseAllWork(@RequestBody workVo workVo) {
        return ResultUtil.success(workService.getCourseAllWork(workVo));
    }

    /**
     * 查询具体作业详情
     * @param workVo
     * @return
     */
    @PostMapping("/getWorkByWorkId")
    public ResultUtil<works> getWorkByWorkId(@RequestBody workVo workVo) {
        return ResultUtil.success(workService.getWorkByWorkId(workVo.getWorkId()));
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
     * courseId chapterId
     */
    @PostMapping("/delWork")
    public ResultUtil<String> delWork(@RequestBody workVo workVo) {
        workService.delWork(workVo);
        return ResultUtil.success();
    }

    /**
     * 教师修改作业
     */
    @PostMapping("/editWork")
    public ResultUtil<String> editWork(@RequestBody works works) {
        workService.editWork(works);
        return ResultUtil.success();
    }

    /**
     * 教师查看学生提交作业详情/可打成绩/可评价
     * courseId chapterId -> work_id->user_work
     */
    @PostMapping("/getSubmitWork")
    public ResultUtil<PageInfo<userWork>> getSubmitWork(@RequestBody workVo workVo){
       workVo.setWorkId(workService.getWorkId(workVo));
        PageInfo<userWork> submitWork = workService.getSubmitWork(workVo);
        return  ResultUtil.success(submitWork);
    }
    /**
     * 批改作业/打分评价
     * userId workId
     */
    @PostMapping("/correctWork")
    public ResultUtil<String> correctWork(@RequestBody userWork userWork){
        workService.correctWork(userWork);
        return  ResultUtil.success();
    }
    /**
     * 学生写作业
     */
    @PostMapping("/doWork")
    public ResultUtil<String> doWork(@RequestBody userWork userWork){
        user loginUser = UserUtil.getLoginUser();
        userWork.setUserId(loginUser.getId());
        workService.doWork(userWork);
        return  ResultUtil.success();
    }


    /**
     * 模糊查询作业根据name
     */
    @PostMapping("/blurWork")
    public ResultUtil<PageInfo<works>> blurWork(@RequestBody workVo workVo){
        return ResultUtil.success(workService.getWorkByBlur(workVo));
    }
}
