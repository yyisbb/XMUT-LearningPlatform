package cn.edu.xmut.learningplatform.service;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.mapper.userMapper;
import cn.edu.xmut.learningplatform.mapper.workMapper;
import cn.edu.xmut.learningplatform.mapper.courseMapper;
import cn.edu.xmut.learningplatform.mapper.chapterMapper;
import cn.edu.xmut.learningplatform.model.*;
import cn.edu.xmut.learningplatform.utils.UserUtil;
import cn.edu.xmut.learningplatform.vo.workVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 李大大
 * @version 1.0
 */
@Service
public class workServiceImpl implements workService {
    @Autowired
    private workMapper workMapper;
    @Autowired
    private courseMapper courseMapper;
    @Autowired
    private chapterMapper chapterMapper;

    @Autowired
    private userMapper userMapper;
    /**
     * 事务
     */
    @Autowired
    private TransactionTemplate transactionTemplate;

    public PageInfo<works> getWorkStatus(workVo workVo, List<works> worksList) {
        if (!ObjectUtils.isEmpty(worksList)) {
            userWork userWork;
            for (works works : worksList) {
                workVo.setWorkId(works.getId());
                userWork = workMapper.getWorkStatus(workVo);
                if (userWork == null) {
                    works.setStatus(0);
                } else if (userWork.getUpFilePath() != null || userWork.getComment() != null) {
                    works.setStatus(1);
                } else {
                    works.setStatus(-1);
                }

            }
        }
        return new PageInfo<>(worksList);
    }

    /**
     * 根据当前学生id查询所有课程作业
     */
    @Override
    public PageInfo<works> getStudentAllWork(user loginUser) {
        workVo workVo = new workVo();
        workVo.setUserId(loginUser.getId());
        List<works> worksList = workMapper.getWorkByStudentId(loginUser.getId());
        for (works works : worksList) {
            works.setChapter(chapterMapper.getChapterById(works.getChapterId()));
            works.setCourse(courseMapper.getCourseByCourseId(works.getCourseId()));
        }
        return getWorkStatus(workVo, worksList);
    }

    /**
     * 教师添加作业
     */
    @Override
    public void addWork(works works) {
        //校验参数
        if (works.getName() == null || works.getName().length() == 0
                || works.getStartTime() == null
                || works.getEndTime() == null
                || works.getChapterId() == null
                || works.getCourseId() == null
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        workVo workVo = new workVo(works.getCourseId(), works.getChapterId());
        works sqlWork = workMapper.getWorkDetails(workVo);
       if (sqlWork!=null){
           Integer id = sqlWork.getId();
           sqlWork = works;
           sqlWork.setId(id);
       }

        if (!ObjectUtils.isEmpty(sqlWork)) {
            //修改作业
            workMapper.editWork(sqlWork);
            return;
        }
        workMapper.addWork(works);
    }

    /**
     * 教师删除作业
     */
    @Override
    public void delWork(workVo workVo) {
        //参数判空
        if (workVo.getCourseId() == null || workVo.getCourseId() == 0
                || workVo.getChapterId() == null || workVo.getChapterId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        workMapper.delWork(workVo);
    }

    /**
     * 教师修改作业
     */
    @Override
    public void editWork(works works) {
        //校验参数
        if (works.getName() == null || works.getName().length() == 0
                || works.getStartTime() == null
                || works.getEndTime() == null
                || works.getChapterId() == null
                || works.getCourseId() == null
        ) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        workMapper.editWork(works);
    }

    /**
     * 获取当前课程下所有作业
     */
    @Override
    public PageInfo<works> getCourseAllWork(workVo courseVo) {

        //参数
        if (ObjectUtils.isEmpty(courseVo)) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //判空
        if (courseVo.getCourseId() == null || courseVo.getCourseId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }

        //查询课程
        course sqlCourse = courseMapper.getCourseByCourseId(courseVo.getCourseId());
        //判断课程是否存在
        if (sqlCourse == null || sqlCourse.getId() == 0) {
            throw new GlobalException(ErrorCode.COURSE_EMPTY_ERROR);
        }

        //带参则分页 否则不分页
        if (courseVo.getCurrent() != 0 && courseVo.getPageSize() != 0) {
            PageHelper.startPage(courseVo.getCurrent(), courseVo.getPageSize());
        }

        //查询作业
        List<works> worksList = workMapper.getWorkByCourseId(courseVo);

        //查询每个作业的章节
        for (works works : worksList) {
            works.setChapter(chapterMapper.getChapterById(works.getChapterId()));
            works.setCourse(courseMapper.getCourseByCourseId(works.getCourseId()));
        }
        //作业状态
        user loginUser = UserUtil.getLoginUser();
        workVo work = new workVo();
        work.setUserId(loginUser.getId());
        return getWorkStatus(work, worksList);
    }

    @Override
    public works getWorkDetails(workVo workVo) {
        //参数校验
        if (workVo.getWorkId() == null || workVo.getWorkId() == 0
                || workVo.getUserId() == null || workVo.getUserId() == 0
                || workVo.getStatus() == null) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //第一次
        if (workVo.getStatus() == 0) {
            workMapper.viewWork(workVo);
        }
        //查询作业
        works sqlWork = workMapper.getWorkDetails(workVo);
        sqlWork.setChapter(chapterMapper.getChapterById(sqlWork.getChapterId()));
        sqlWork.setCourse(courseMapper.getCourseByCourseId(sqlWork.getCourseId()));
        //拿到已经写过的作业
        if (workVo.getStatus() == 1) {
            List<userWork> userWorkList = workMapper.getSubmitWork(workVo);
            System.out.println(userWorkList);
            sqlWork.setUpFilePath(userWorkList.get(0).getUpFilePath());
            sqlWork.setComment(userWorkList.get(0).getComment());
        }

        if (ObjectUtils.isEmpty(sqlWork)) {
            throw new GlobalException(ErrorCode.WORK_EMPTY_ERROR);
        }

        return sqlWork;
    }


    /**
     * 获取学生提交的作业
     */
    @Override
    public PageInfo<userWork> getSubmitWork(workVo workVo) {
        //参数校验
        if (workVo.getCourseId() == null || workVo.getCourseId() == 0 ||
                workVo.getChapterId() == null || workVo.getChapterId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        //查询作业
        List<userWork> userWorkList = workMapper.getSubmitWork(workVo);
        return new PageInfo<>(userWorkList);
    }

    /**
     * 批改作业/打分评价
     * userId workId
     *
     * @param userWork
     */
    @Override
    public void correctWork(userWork userWork) {
        //参数校验
        if (userWork.getScore() == null) {
            throw new GlobalException(ErrorCode.SCORE_EMPTY_ERROR);
        }
        workMapper.correctWork(userWork);
    }


    /**
     * 交作业
     */
    @Override
    public void doWork(workVo workVo) {
        //参数校验
        if (workVo.getWorkId() == null || workVo.getWorkId() == 0
                || workVo.getUserId() == null || workVo.getUserId() == 0) {
            throw new GlobalException(ErrorCode.PARAMETER_EMPTY_ERROR);
        }
        if (workVo.getUpFilePath() == null && workVo.getComment() == null) {
            throw new GlobalException(ErrorCode.WORKUP_EMPTY_ERROR);
        }
        //没有这份作业时
        if (workMapper.doWork(workVo) == 0) {
            throw new GlobalException(ErrorCode.WORK_EMPTY_ERROR);
        }
    }


    @Override
    public List<user> releaseMutual(mutual mutual) {
        //查询当前作业是否存在
        works workByWorkId = workMapper.getWorkDetails(new workVo(mutual.getWorkId()));
        if (ObjectUtils.isEmpty(workByWorkId)) {
            throw new GlobalException(ErrorCode.WORK_EMPTY_ERROR);
        }


        //作业存在发布互改
        //查询所有学生
        List<user> courseUserList = courseMapper.getUserListByCourseId(//查询当前作业所在的课程
                workByWorkId.getCourseId());

        //打乱学生
        Collections.shuffle(courseUserList);

        //生成互改
        for (int i = 0; i < courseUserList.size(); i++) {
            user currentUser = courseUserList.get(i);
            if (currentUser.getId() == 1) {
                continue;
            }
            currentUser.setGradingUsers(new ArrayList<>());
            for (int j = 1; j <= 3; j++) {
                user gradingUser = courseUserList.get((i + j) % courseUserList.size());
                currentUser.getGradingUsers().add(gradingUser.getId());
            }
        }

        //开事务
        transactionTemplate.execute(status -> {
            try {

                //修改当前作业互改状态
                int count = workMapper.updateMutual(1, mutual.getWorkId());
                if (count == 0) {
                    throw new GlobalException(ErrorCode.SQL_ERROR);
                }

                for (user user : courseUserList) {
                    if (user.getId() == 1) {
                        continue;
                    }
                    for (int i = 0; i < user.getGradingUsers().size(); i++) {
                        //插入互改
                        int c = workMapper.insertMutual(new mutual(user.getId(), user.getGradingUsers().get(i), mutual.getWorkId()));
                        if (c == 0) {
                            throw new GlobalException(ErrorCode.SQL_ERROR);
                        }
                    }
                }

            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
            return null;
        });


        return courseUserList;
    }

    @Override
    public List<userWork> getMutualWork(mutual mutual) {
        //查询互改列表
        Integer userId = UserUtil.getLoginUser().getId();
        List<userWork> mutualWork = workMapper.getMutualWork(new mutual(userId, mutual.getWorkId()));
        for (userWork userWork : mutualWork) {
            userWork.setUser(userMapper.getUserInfoByUserId(userWork.getUserId()));
            userWork.setWorks(workMapper.getWorkDetails(new workVo(userWork.getWorkId())));
            userWork.setScore(workMapper.getMutual(new mutual(userId, userWork.getUserId(), mutual.getWorkId())).getGrade());
        }
        return mutualWork;
    }

    @Override
    public mutual computeScore(mutual mutual) {
        mutual.setGradedUserId(UserUtil.getLoginUser().getId());
        Integer grade = workMapper.computeScore(mutual);
        mutual.setGrade(grade/3);
        return mutual;
    }


    /**
     * 批改作业/打分评价
     * userId workId
     *
     * @param mutual
     */
    @Override
    public void mutualCorrectWork(mutual mutual) {
        //参数校验
        if (mutual.getGrade() == null || mutual.getGradedUserId() == null || mutual.getWorkId() == null) {
            throw new GlobalException(ErrorCode.SCORE_EMPTY_ERROR);
        }

        mutual.setUserId(UserUtil.getLoginUser().getId());

        int count = workMapper.mutualCorrectWork(mutual);
        if (count == 0) {
            throw new GlobalException(ErrorCode.SQL_ERROR);
        }
    }

}
