package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.workMapper;
import cn.edu.xmut.learningplatform.model.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 李大大
 * @version 1.0
 */
@Service
public class workServiceImpl implements workService {
    @Autowired
    private workMapper workMapper;

    /**
     * 根据当前学生id查询作业
     */
    @Override
    public PageInfo<works> getStudentAllWork(user loginUser) {
        List<works> worksList = workMapper.getWorkByStudentId(loginUser.getId());
        return new PageInfo<>(worksList);
    }


    /**
     * 教师添加作业
     */
    @Override
    public void addWork(works works) {
        if (ObjectUtils.isEmpty(works)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //校验参数
        if (works.getName() == null || works.getName().length() == 0
                || works.getStartTime() == null
                || works.getEndTime() == null
                || works.getChapterId() == null
                || works.getCourseId() == null
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        workMapper.addWork(works);
    }

    /**
     * 教师删除作业
     */
    @Override
    public void delWork(works works) {
        //TODO 健壮性校验
        workMapper.delWork(works);
    }

    /**
     * 教师修改作业
     */
    @Override
    public void editWork(works works) {
        //TODO 健壮性校验
        workMapper.editWork(works);
    }
}