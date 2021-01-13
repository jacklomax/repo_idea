package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;

import java.util.List;


public interface CourseMapper {

    public List<Course> findCourseByCondition(CourseVo courseVo);

    public void saveCourse(Course course);

    public void saveTeacher(Teacher teacher);

    public CourseVo findCourseById(Integer id);

    public void updateCourse(Course course);

    public void updateTeacher(Teacher teacher);

    public void updateCourseStatus(Course course);

}
