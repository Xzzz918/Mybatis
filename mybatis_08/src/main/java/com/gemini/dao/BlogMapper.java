package com.gemini.dao;

import com.gemini.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author gemini
 * Created in  2021/5/2 20:25
 */
public interface BlogMapper {
//    插入数据
    int addBlog(Blog blog);
//    查询博客
    List<Blog> queryBlogIF(Map<String,Object> map);
    List<Blog> queryBlogChoose(Map<String,Object> map);
//    更新博客
    int updateBlogSet(Map<String,Object> map);
//    查询第1——3号的博客
    List<Blog> queryBlogForeach(Map<String,Object> map);
}
