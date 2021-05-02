package com.gemini.mapper;

import com.gemini.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author gemini
 * Created in  2021/5/2 11:29
 */
public interface TeachMapper {
    @Select("select * from mybatis.teacher where id = #{id}")
    Teacher getTeach(@Param("id") int id);
}
