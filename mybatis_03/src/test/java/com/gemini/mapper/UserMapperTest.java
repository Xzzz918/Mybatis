package com.gemini.mapper;

import com.gemini.pojo.User;
import com.gemini.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

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

}
