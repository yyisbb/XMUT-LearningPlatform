package cn.edu.xmut.learningplatform.controller;

import cn.edu.xmut.learningplatform.model.task;
import cn.edu.xmut.learningplatform.service.taskService;
import cn.edu.xmut.learningplatform.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class taskController {
    @Autowired
    private taskService taskService;
    @PostMapping("/addTask")
    public ResultUtil<String> addTask(@RequestBody task task) {
        taskService.addTask(task);
        return ResultUtil.success();
    }

    @PostMapping("/deleteTask")
    public ResultUtil<String> deleteTask(@RequestBody task task) {
        taskService.deleteTask(task);
        return ResultUtil.success();
    }

    @PostMapping("/updateTaskVideo")
    public ResultUtil<String> updateTaskVideo(@RequestBody task task){
        taskService.updateTaskVideo(task);
        return ResultUtil.success();
    }
}
