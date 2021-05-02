package com.gemini.mapper;

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
            Teacher teacher = sqlSession.getMapper(TeachMapper.class).getTeach(1);
            System.out.println(teacher);
        }
    }

    @Test
    public void teachMapperTest1(){
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()){
            List<Student> students = sqlSession.getMapper(StuMapper.class).getStu();
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    @Test
    public void teachMapperTest2(){
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()){
            List<Student> students = sqlSession.getMapper(StuMapper.class).getStu2();
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}
