package com.gemini.mapper;

import com.gemini.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
//    方法存在多个参数,所有的非引用对象参数前面必须加上@Param("对应于select语句中#{}里面的部分")注解
//    如果只有一个基本类型参数,那么可以忽略,但是建议加上
//    即@Param("A")对应于select注解的语句中的"#{A}"
    @Select("select * from mybatis.user where id = #{Id}")
    User getUserById(@Param("Id") int id);

    @Insert("insert into mybatis.user(id,name,pwd) values (#{id},#{name},#{pwd})")
    void addUser(User user);

    @Update("update mybatis.user set id = #{id}, pwd = #{pwd} where name = #{name}")
    void update(User user);

    @Delete("delete from mybatis.user where id = #{id}")
    void deleteUser(@Param("id") int id);
}

