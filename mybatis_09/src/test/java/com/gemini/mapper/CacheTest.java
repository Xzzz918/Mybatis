package com.gemini.mapper;

import com.gemini.dao.UserMapper;
import com.gemini.pojo.User;
import com.gemini.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author gemini
 * Created in  2021/5/3 14:39
 */
public class CacheTest {
    @Test
    public void test(){
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()){
            User user = sqlSession.getMapper(UserMapper.class).queryUserById(2);
            System.out.println(user);
            System.out.println("=============================================");
//            sqlSession.clearCache();//手动清除一级缓存
//            User user1 = sqlSession.getMapper(UserMapper.class).queryUserById(2);
//            System.out.println(user1);
//            System.out.println(user==user1);
        }
        try(SqlSession sqlSession1 = MyBatisUtil.getSqlSession()) {
            User user1 = sqlSession1.getMapper(UserMapper.class).queryUserById(2);
            System.out.println(user1);
        }
    }
}
