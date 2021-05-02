package com.gemini.mapper;

import com.gemini.pojo.Student;

import java.util.List;

/**
 * @author gemini
 * Created in  2021/5/2 11:28
 */
public interface StuMapper {
//    查询所有的学生信息以及对应的老师信息
    List<Student> getStu();
    List<Student> getStu2();
}
