package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.mutual;
import cn.edu.xmut.learningplatform.model.userWork;
import cn.edu.xmut.learningplatform.model.works;
import cn.edu.xmut.learningplatform.model.user;
import cn.edu.xmut.learningplatform.service.workService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import cn.edu.xmut.learningplatform.vo.workVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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
     * 按课程查询作业/模糊
     */
    @PostMapping("/getCourseAllWork")
    public ResultUtil<PageInfo<works>> getCourseAllWork(@RequestBody workVo workVo) {
        return ResultUtil.success(workService.getCourseAllWork(workVo));
    }

    /**
     * 查询具体作业详情/第一次/继续
     * @param workVo
     * @return
     */
    @PostMapping("/getWorkDetails")
    public ResultUtil<works> getWorkDetails(@RequestBody workVo workVo) {
        user loginUser = UserUtil.getLoginUser();
        workVo.setUserId(loginUser.getId());
        return ResultUtil.success(workService.getWorkDetails(workVo));
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
     * 学生交作业/重新提交作业
     */
    @PostMapping("/doWork")
    public ResultUtil<String> doWork(@RequestBody workVo workVo){
        user loginUser = UserUtil.getLoginUser();
        workVo.setUserId(loginUser.getId());
        workService.doWork(workVo);
        return  ResultUtil.success();
    }



    /**
     * 发布互改
     */
    @PostMapping("/releaseMutual")
    public ResultUtil<List<user>> releaseMutual(@RequestBody mutual mutual) {
        if (ObjectUtils.isEmpty(mutual.getWorkId())) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        return ResultUtil.success(workService.releaseMutual(mutual));
    }
}
