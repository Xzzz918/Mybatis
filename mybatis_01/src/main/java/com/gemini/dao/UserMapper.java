package com.gemini.dao;

import com.gemini.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author gemini
 * Created in  2021/4/27 19:19
 */
public interface UserMapper {
//    查询全部用户
    List<User> getUserList();
//    根据id查询用户
    User getUserById(int id);
    User getUserById2(Map<String,Object> map);
//    insert一个用户
    void insertUser(User user);
//    万能Map
    void addUser(Map<String,Object> map);
//    修改用户
    void update(User user);
//    删除用户
    void deleteUser(int id);
//    模糊查询
    List<User> getUserLike(String value);
}

