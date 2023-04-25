package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.taskMapper;
import cn.edu.xmut.learningplatform.mapper.previewMapper;
import cn.edu.xmut.learningplatform.model.chapter;
import cn.edu.xmut.learningplatform.mapper.chapterMapper;
import cn.edu.xmut.learningplatform.model.course;
import cn.edu.xmut.learningplatform.model.preview;
import cn.edu.xmut.learningplatform.model.task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class chapterServiceImpl implements chapterService {

    @Autowired
    private courseMapper courseMapper;
    @Autowired
    private chapterMapper chapterMapper;

    @Autowired
    private taskMapper taskMapper;

    @Autowired
    private previewMapper previewMapper;

    @Override
    public List<chapter> getCourseAllChapter(chapter chapter) {
        //参数空
        if (ObjectUtils.isEmpty(chapter)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //courseId为0
        if(chapter.getCourseId()==null||chapter.getCourseId()==0){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //查课程是否存在
        course sqlCourse = courseMapper.getCourseByCourseId(chapter.getCourseId());
        if (ObjectUtils.isEmpty(sqlCourse)){
            throw new GlobalException(ErrorCode.COURSE_EMPTY_ERROR);
        }

        //课程存在 查询当前课程下的所有章节
        List<chapter> allChapter = chapterMapper.getAllChapterByCourseId(sqlCourse.getId());

        //遍历章节拿任务点
        for (chapter c : allChapter) {
            c.setTaskList(taskMapper.getAllTaskByChapterId(c.getId()));
        }


        return allChapter;
    }

    @Override
    public void addChapterPPT(chapter chapter) {
        //参数空
        if (ObjectUtils.isEmpty(chapter)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //chapterId为0
        if(chapter.getId()==null||chapter.getId()==0||chapter.getPptUrl()==null||chapter.getPptUrl().length()==0){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //章节是否存在
        chapter sqlChapter = chapterMapper.getChapterById(chapter.getId());
        if (ObjectUtils.isEmpty(sqlChapter)){
            throw new GlobalException(ErrorCode.CHAPTER_EMPTY_ERROR);
        }

        //创建查看PPTURl
        chapter.setViewPPtUrl("https://view.officeapps.live.com/op/embed.aspx?src="+chapter.getPptUrl());

        //设置PPT Url
        if (chapterMapper.updateChapter(chapter)==0){
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public void createChapter(chapter chapter) {
        //参数空
        if (ObjectUtils.isEmpty(chapter)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        if(
                chapter.getName()==null||chapter.getName().length()==0||
                chapter.getCourseId()==null||chapter.getCourseId()==0
        ){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //创建章节
         chapterMapper.createChapter(chapter);
        if (chapter.getId()==null||chapter.getId()==0){
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public void deleteChapter(chapter chapter) {
        //参数空
        if (ObjectUtils.isEmpty(chapter)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if(
                chapter.getId()==null||chapter.getId()==0
        ){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询章节是否存在
        if (ObjectUtils.isEmpty(chapterMapper.getChapterById(chapter.getId()))){
            throw new GlobalException(ErrorCode.CHAPTER_EMPTY_ERROR);
        }


        //查询下面是否有任务点
        List<task> taskList = taskMapper.getAllTaskByChapterId(chapter.getId());
        if (taskList!=null&&taskList.size()!=0){
            throw new GlobalException(ErrorCode.TASK_NOT_EMPTY_EMPTY_ERROR);
        }

        if (chapterMapper.deleteChapter(chapter)==0){
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }

    }

    @Override
    public void releasePreView(preview preview) {
        //参数空
        if (ObjectUtils.isEmpty(preview)){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        if(
                preview.getChapterId()==null|| preview.getChapterId()==0||
                        preview.getName()==null|| preview.getName().length()==0||
                        preview.getStartTime() == null ||
                        preview.getEndTime() == null
        ){
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }


        //查询章节是否存在
        if (ObjectUtils.isEmpty(chapterMapper.getChapterById(preview.getChapterId()))){
            throw new GlobalException(ErrorCode.CHAPTER_EMPTY_ERROR);
        }

        //预习已经存在 要先删除
        if (!ObjectUtils.isEmpty(previewMapper.getPreViewByChapterId(preview.getChapterId()))){
            throw new GlobalException(ErrorCode.PREVIEW_EXIST_ERROR);
        }
        //创建预习
        previewMapper.addPreView(preview);
    }
}
