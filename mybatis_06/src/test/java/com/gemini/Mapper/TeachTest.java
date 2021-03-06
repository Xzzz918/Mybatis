package com.gemini.Mapper;

import com.gemini.mapper.StuMapper;
import com.gemini.mapper.TeachMapper;
import com.gemini.pojo.Student;
import com.gemini.pojo.Teacher;
import com.gemini.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author gemini
 * Created in  2021/5/2 16:25
 */
public class TeachTest {

    @Test
    public void teachMapperTest(){
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()){
            List<Teacher> teachers = sqlSession.getMapper(TeachMapper.class).getTeacher();
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        }
    }

    @Test
    public void teachMapperTest1(){
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()){
            Teacher teacher = sqlSession.getMapper(TeachMapper.class).getTeacherStu(1);
            System.out.println(teacher);
        }
    }

    @Test
    public void teachMapperTest2(){
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()){
            Teacher teacher = sqlSession.getMapper(TeachMapper.class).getTeacherStu2(1);
            System.out.println(teacher);
        }
    }
}
