package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        List<Course> list = courseMapper.findCourseByCondition(courseVo);
        return list;
    }

    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) {

        Course course = new Course();
        BeanUtils.copyProperties(courseVo,course);
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        courseMapper.saveCourse(course);

        int id = course.getId();

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVo,teacher);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVo findCourseById(Integer id){
        CourseVo courseVo = courseMapper.findCourseById(id);
        return courseVo;
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) {

        Course course = new Course();
        BeanUtils.copyProperties(courseVo,course);
        Date date = new Date();
        course.setUpdateTime(date);
        courseMapper.updateCourse(course);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVo,course);
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);
        courseMapper.updateTeacher(teacher);

    }

    @Override
    public void updateCourseStatus(int courseid,int status) {
        Course course = new Course();
        course.setId(courseid);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        courseMapper.updateCourseStatus(course);
    }


}
