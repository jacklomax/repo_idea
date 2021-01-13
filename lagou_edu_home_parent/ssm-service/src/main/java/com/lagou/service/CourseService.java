package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;

import java.util.List;

public interface CourseService {

    public List<Course> findCourseByCondition(CourseVo courseVo);

    public void saveCourseOrTeacher(CourseVo courseVo);

    public CourseVo findCourseById(Integer id);

    public void updateCourseOrTeacher(CourseVo courseVo);

    public void updateCourseStatus(int courseid,int status);
}
