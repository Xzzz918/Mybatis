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
            List<User> userList = userMapper.getUserList();

            for (User user : userList) {
                System.out.println(user);
            }
        }
    }

}
