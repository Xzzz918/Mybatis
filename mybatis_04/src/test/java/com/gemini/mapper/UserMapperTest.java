package com.gemini.mapper;

import com.gemini.pojo.User;
import com.gemini.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gemini
 * Created in  2021/4/27 19:29
 */
public class UserMapperTest {

    //无参数查
    @Test
    public void testUserMapper() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            List<User> userList = sqlSession.getMapper(UserMapper.class).getUsers();
            for (User user : userList) {
                System.out.println(user);
            }
        }
    }

    //有参数查
    @Test
    public void testUserMapper1() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            User user = sqlSession.getMapper(UserMapper.class).getUserById(2);
            System.out.println(user);
        }
    }

    //增
    @Test
    public void testUserMapper2() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            //不需要再提交事务,因为com.gemini.utils.MyBatisUtil.getSqlSession已经自动提交事务
            sqlSession.getMapper(UserMapper.class).addUser(new User(6, "GodMi", "36252"));
        }
    }

    //改
    @Test
    public void testUserMapper3() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            //不需要再提交事务,因为com.gemini.utils.MyBatisUtil.getSqlSession已经自动提交事务
//            sqlSession.getMapper(UserMapper.class).update(new User(1, "Gemini", "36252"));
            sqlSession.getMapper(UserMapper.class).update(new User(5, "GodMi", "793400"));
        }
    }

    //删
    @Test
    public void testUserMapper4() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            //不需要再提交事务,因为com.gemini.utils.MyBatisUtil.getSqlSession已经自动提交事务
            sqlSession.getMapper(UserMapper.class).deleteUser(5);
        }
    }



}
