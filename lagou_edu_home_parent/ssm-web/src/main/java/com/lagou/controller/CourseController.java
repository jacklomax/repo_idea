package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){

        List<Course> list = courseService.findCourseByCondition(courseVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));
        String originalFilename = file.getOriginalFilename();
        String newFilename = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        String uploadPath=substring+"upload\\";
        File filePath = new File(uploadPath, newFilename);
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录"+filePath);
        }

        file.transferTo(filePath);
        Map<String, String> map=new HashMap<>();
        map.put("fileName",newFilename);
        map.put("filePath","http://localhost:8080/upload/"+newFilename);
        ResponseResult result = new ResponseResult(true, 200, "图片上传成功", map);
        return result;
    }

    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo  courseVo){
        ResponseResult result;
        if(courseVo.getId()==null){
            courseService.saveCourseOrTeacher(courseVo);
            result = new ResponseResult(true, 200, "新增成功", null);
        }else{
            courseService.updateCourseOrTeacher(courseVo);
            result = new ResponseResult(true, 200, "修改成功", null);
        }
        return result;
    }


    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVo courseVo = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "根据ID查询课程信息成功", courseVo);
        return result;
    }


    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){
        courseService.updateCourseStatus(id,status);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "课程状态变更成功", map);
        return result;
    }
}
