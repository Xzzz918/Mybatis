package com.gemini.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @author gemini
 * Created in  2021/5/2 11:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String name;

//    学生需要关联一个老师
    private Teacher teacher;
}
