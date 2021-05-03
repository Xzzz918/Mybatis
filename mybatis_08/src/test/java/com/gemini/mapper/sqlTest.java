package com.gemini.mapper;

import com.gemini.dao.BlogMapper;
import com.gemini.pojo.Blog;
import com.gemini.utils.IDUtils;
import com.gemini.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

/**
 * @author gemini
 * Created in  2021/5/2 21:54
 */
public class sqlTest {
    @Test
    public void teachMapperTest(){
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()){
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setId(IDUtils.getId());
            blog.setTitle("Mybatis如此简单");
            blog.setAuthor("狂神说");
            blog.setCreateTime(new Date());
            blog.setViews(9999);

            mapper.addBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("Java如此简单");
            mapper.addBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("Spring如此简单");
            mapper.addBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("微服务如此简单");
            mapper.addBlog(blog);
        }
    }

    @Test
    public void queryBlogIFTest(){
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()){
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            //什么都不输入的情况，应该会输出所有的值
            Map<String, Object> map = new HashMap<>();
            //通过不同的参数实现动态搜索功能
            map.put("title","Java如此简单");
            map.put("author", "狂神说");
            List<Blog> blogs = mapper.queryBlogIF(map);
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        }
    }

    @Test
    public void queryBlogChooseTest() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            //什么都不输入的情况，应该会输出所有的值
            Map<String, Object> map = new HashMap<>();
            //类似于switch case，只要满足一个就不会管其他的满不满足
            map.put("title","Java如此简单");
            map.put("author", "狂神说");
            map.put("id", "d59d48b2b81349269815368d577dd882");
            map.put("views", 9999);
            List<Blog> blogs = mapper.queryBlogChoose(map);
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        }
    }

    @Test
    public void updateBlogSetTest() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Map<String, Object> map = new HashMap<>();
//            map.put("title","Java如此简单!");
//            update mybatis.blog SET author = ? where id = ?
            map.put("author", "狂神说!");
            map.put("id", "d59d48b2b81349269815368d577dd882");
            mapper.updateBlogSet(map);
        }
    }

    @Test
    public void queryBlogForeachTest(){
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()){
            Map<String,Object> map = new HashMap<>();
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            integers.add(2);
            integers.add(3);
            integers.add(4);
            map.put("ids", integers);
            List<Blog> blogs = sqlSession.getMapper(BlogMapper.class).queryBlogForeach(map);
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        }
    }
}
