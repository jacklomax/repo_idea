package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){

        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "章节及课时信息查询成功", list);
        return result;
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "查询课程信息成功", course);
        return result;
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if(courseSection.getId()==null){
            courseContentService.saveSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "新增章节成功", null);
            return result;
        }else{
            courseContentService.updateSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "修改章节成功", null);
            return result;
        }


    }


    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){

        courseContentService.updateSectionStatus(id,status);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改章节状态成功", map);
        return result;
    }

}
