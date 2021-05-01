package com.gemini.mapper;

import com.gemini.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author gemini
 * Created in  2021/4/27 19:19
 */
public interface UserMapper {
//注解在接口上实现
//    其本质为反射机制,底层为动态代理
//    代理模式必须了解,面试高频:动态代理,工厂模式,单例模式(手写)
    @Select("select * from mybatis.user")
    List<User> getUsers();
}

