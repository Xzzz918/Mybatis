package com.gemini.dao;

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
    @Test
    public void testUserMapper() {
//        1、获得sqlSession对象
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            //        2、执行SQL
            //        方式：sqlSession.getMapper(UserMapper.class)
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();

            for (User user : userList) {
                System.out.println(user);
            }
//            3、通过try-with-resources实现确保 SqlSession 关闭，因此下面的语句不再显式需要
//            sqlSession.close();
        }
    }
//    outputs:
//        User{id=1, name='Gemini', pwd='123456'}
//        User{id=2, name='Xio', pwd='123456'}

    @Test
    public void testUserMapper1() {
//        1、获得sqlSession对象
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            //        2、执行SQL
            //        方式：sqlSession.getMapper(UserMapper.class)
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUserById(1);
            System.out.println(user);
//            3、通过try-with-resources实现确保 SqlSession 关闭，因此下面的语句不再显式需要
//            sqlSession.close();
        }
    }
//outputs:
//        User{id=1, name='Gemini', pwd='123456'}

    //增删改需要提交事务
    @Test
    public void testUserMapper2() {
//        1、获得sqlSession对象
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            //        2、执行SQL
            //        方式：sqlSession.getMapper(UserMapper.class)
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.insertUser(new User(3, "张笑", "123456"));
            //提交事务
            sqlSession.commit();
        }
    }
    @Test
    public void testUserMapper3() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.update(new User(3, "嚣张", "123456"));
            //提交事务
            sqlSession.commit();
        }
    }
    @Test
    public void testUserMapper4() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            sqlSession.getMapper(UserMapper.class).deleteUser(3);
            //提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void testUserMapper5() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            //    Map传递参数，直接在sql中取出key即可；
            //    对象传递参数，直接在sql中取对象属性即可。
            Map<String,Object> map = new HashMap<>();
            map.put("UserId", 5);
            map.put("UserName", "Hemini");
            map.put("UserPwd", "123456");
            sqlSession.getMapper(UserMapper.class).addUser(map);
            //提交事务
            sqlSession.commit();
        }
    }

    @Test
    public void testUserMapper6() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String,Object> map = new HashMap<>();
            map.put("UserId", 4);
            map.put("UserName", "gemini");
            User user = sqlSession.getMapper(UserMapper.class).getUserById2(map);
            System.out.println(user);
        }
        //    outputs:
        //    User{id=4, name='gemini', pwd='123456'}
    }

    @Test
    public void testUserMapper7() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            List<User> userList = sqlSession.getMapper(UserMapper.class).getUserLike("%emini");
            for (User user : userList) {
                System.out.println(user);
            }
        }
        //    outputs:
//        User{id=1, name='Gemini', pwd='123456'}
//        User{id=4, name='gemini', pwd='123456'}
//        User{id=5, name='Hemini', pwd='123456'}
    }
}
