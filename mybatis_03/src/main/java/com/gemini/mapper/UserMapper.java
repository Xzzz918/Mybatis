package com.gemini.mapper;

import com.gemini.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author gemini
 * Created in  2021/4/27 19:19
 */
public interface UserMapper {
    User getUserById(int id);

    //分页
    List<User> getUserByLimit(Map<String,Integer> map);
}

