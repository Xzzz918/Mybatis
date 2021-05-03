package com.gemini.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author gemini
 * Created in  2021/4/27 19:10
 * 工具类
 */
public class MyBatisUtil {
    //使用Mybatis第一步：获取sqlSessionFactory对象
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获得sqlSession实例
    //SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
    //autoCommit设置为True,即可自动提交事务
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }

}
