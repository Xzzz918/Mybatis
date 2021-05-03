package com.gemini.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author gemini
 * Created in  2021/5/2 20:20
 */
@Data
public class Blog {
    private String id;
    private String title;
    private String author;
    private Date createTime;    //属性名和字段名不一致
    private int views;

}
