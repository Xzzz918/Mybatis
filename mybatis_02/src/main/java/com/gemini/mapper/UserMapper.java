package com.gemini.mapper;

import com.gemini.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author gemini
 * Created in  2021/4/27 19:19
 */
public interface UserMapper {
    List<User> getUserList();
    User getUserById(int id);
    void insertUser(User user);
    void update(User user);
    void deleteUser(int id);
}

