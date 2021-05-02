package com.gemini.mapper;

import com.gemini.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gemini
 * Created in  2021/5/2 11:29
 */
public interface TeachMapper {
    //获取所有老师
    List<Teacher> getTeacher();

    //获取指定老师下的所有学生及老师信息
    //方式一：按照结果查询
    Teacher getTeacherStu(@Param("tid") int id);
    //方式二：子查询
    Teacher getTeacherStu2(@Param("tid") int id);
}
