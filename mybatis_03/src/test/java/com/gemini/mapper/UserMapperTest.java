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
    @Test
    public void testUserMapper() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUserById(2);

            System.out.println(user);
        }
    }

    @Test
    public void testUserMapper1() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String,Integer> map = new HashMap<>();
            map.put("startIndex", 1);
            map.put("pageSize", 2);
            List<User> list = sqlSession.getMapper(UserMapper.class).getUserByLimit(map);
            for (User user : list) {
                System.out.println(user);
            }
        }
    }

}
