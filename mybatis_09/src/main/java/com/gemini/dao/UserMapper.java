package com.gemini.dao;

import com.gemini.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gemini
 * Created in  2021/5/3 14:32
 */
public interface UserMapper {
    User queryUserById(@Param("id") int id);
}
