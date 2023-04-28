package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.mapper.previewMapper;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.preview;
import cn.edu.xmut.learningplatform.model.userCourse;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
@Service
public class previewServiceImpl implements previewService{
    @Autowired
    private previewMapper previewMapper;

    /**
     * 学生查询当前课程的预习点
     * @param course
     * @return
     */
    @Override
    public PageInfo<preview> selectByIdPreview(course course) {
        if (course.getCurrent() != null &&
                course.getPageSize() != null&&
                course.getCurrent() !=0&&
                course.getPageSize() !=0
        ) {
            PageHelper.startPage(course.getCurrent(), course.getPageSize());
        }
        List<preview> previews = previewMapper.selectByIdPreview(course.getId());
        if (ObjectUtils.isEmpty(previews)||previews.size()==0){
            throw new GlobalException(ErrorCode.PREVIEW_EMPTY_ERROR);
        }
        return new PageInfo<>(previews);
    }
}
