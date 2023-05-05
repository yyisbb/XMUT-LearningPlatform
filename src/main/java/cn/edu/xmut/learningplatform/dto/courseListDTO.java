package cn.edu.xmut.learningplatform.dto;

import cn.edu.xmut.learningplatform.model.course;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class courseListDTO {
   private  PageInfo<course> nowCourseList;
   private  PageInfo<course> beforeCourseList;

    public courseListDTO(List<course> nowCourseList, List<course> beforeCourseList) {
        this.nowCourseList = new PageInfo<course>(nowCourseList);
        this.beforeCourseList = new PageInfo<course>(beforeCourseList);
    }
}
