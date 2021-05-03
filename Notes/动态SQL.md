## 动态SQL

动态SQL就是指根据不同的条件生成不同的SQL语句

在 MyBatis 之前的版本中，需要花时间了解大量的元素。借助功能强大的基于 OGNL 的表达式，MyBatis 3 替换了之前的大部分元素，大大精简了元素种类，现在要学习的元素种类比原来的一半还要少。

- if
- choose (when, otherwise)
- trim (where, set)
- foreach

### 搭建环境

```sql
CREATE TABLE `blog`(
`id` VARCHAR(50) NOT NULL COMMENT 博客id,
`title` VARCHAR(100) NOT NULL COMMENT 博客标题,
`author` VARCHAR(30) NOT NULL COMMENT 博客作者,
`create_time` DATETIME NOT NULL COMMENT 创建时间,
`views` INT(30) NOT NULL COMMENT 浏览量
)ENGINE=INNODB DEFAULT CHARSET=utf8
```

创建一个基础工程：

- 导入包

- 编写配置文件

- 编写实体类

  ```java
  @Data
  public class Blog {
      private String id;
      private String title;
      private String author;
      private Date createTime;
      private int views;
  
  } 
  ```

  

- 编写实体类对应的Mapper接口和Mapper.xml文件

  

### IF

 ```xml
 <select id="queryBlogIF" parameterType="map" resultType="blog">
         select * from mybatis.blog
         <where>
             <if test="title != null">
                 and title = #{title}
             </if>
             <if test="author != null">
                 and author = #{author}
             </if>
         </where>
     </select>
 ```

```java
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
```

### choose (when, otherwise)

```xml
<select id="queryBlogChoose" parameterType="map" resultType="blog">
        select * from mybatis.blog
        <where>
            <choose>
                <when test="title != null">
                    title = #{title}
                </when>
                <when test="author != null">
                    author = #{author}
                </when>
                <when test="id != null">
                    id = #{id}
                </when>
                <otherwise>
                    views = #{views}
                </otherwise>
            </choose>
        </where>
    </select>
```



```java
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
```

### trim (where, set)

#### where元素

*where* 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。而且，若子句的开头为 “AND” 或 “OR”，*where* 元素也会将它们去除。

```xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG
  <where>
    <if test="state != null">
         state = #{state}
    </if>
    <if test="title != null">
        AND title like #{title}
    </if>
    <if test="author != null and author.name != null">
        AND author_name like #{author.name}
    </if>
  </where>
</select>
```

#### Set

*set* 元素会动态地在行首插入 SET 关键字，并会删掉额外的逗号（这些逗号是在使用条件语句给列赋值时引入的）。

```xml
<update id="updateBlogSet" parameterType="map">
    update mybatis.blog
    <set>
        <if test="title != null">
            title = #{title},
        </if>
        <if test="author != null">
            author = #{author}
        </if>
    </set>
    where id = #{id}
</update>
```

#### trim

where和set都是trim元素的一种形式，如果 *where* 元素与你期望的不太一样，你也可以通过自定义 trim 元素来定制 *where* 元素的功能。比如，和 *where* 元素等价的自定义 trim 元素为：

```xml
<trim prefix="WHERE" prefixOverrides="AND |OR ">
  ...
</trim>
```

*prefixOverrides* 属性会忽略通过管道符分隔的文本序列（注意此例中的空格是必要的）。上述例子会移除所有 *prefixOverrides* 属性中指定的内容，并且插入 *prefix* 属性中指定的内容。

与 *set* 元素等价的自定义 *trim* 元素如下：

```xml
<trim prefix="SET" suffixOverrides=",">
  ...
</trim>
```

注意，我们覆盖了后缀值设置，并且自定义了前缀值。

### 阶段总结

**所谓的动态SQL，本质上还是SQL语句，只是我们可以在SQL层面去执行一个逻辑(if,where,choose,when等)代码。**

### Foreach

动态 SQL 的另一个常见使用场景是对集合进行遍历（尤其是在构建 IN 条件语句的时候）。

```xml
<select id="selectPostIn" resultType="domain.blog.Post">
  SELECT *
  FROM POST P
  WHERE ID in
  <foreach item="item" index="index" collection="list"
      open="(" separator="," close=")">
        #{item}
  </foreach>
</select>
```

*foreach* 元素允许你指定一个集合，声明可以在元素体内使用的集合项（item）和索引（index）变量。它也允许你指定开头与结尾的字符串以及集合项迭代之间的分隔符。这个元素也不会错误地添加多余的分隔符。

**提示** 你可以将任何可迭代对象（如 List、Set 等）、Map 对象或者数组对象作为集合参数传递给 *foreach*。当使用可迭代对象或者数组时，index 是当前迭代的序号，item 的值是本次迭代获取到的元素。当使用 Map 对象（或者 Map.Entry 对象的集合）时，index 是键，item 是值。

#### 测试

![image-20210503104435015](noteImages/image-20210503104435015.png)

方便起见，将id更改为1234。

```xml
<!--    老师的思路：
        select * from mybatis.blog where 1=1 and (id=1 or id=2 or id=3)
        foreach collection="ids" item="id" open="and (" close=")" separator="or">
            id = #{id}
        </foreach>
        我的思路：
        select * mybatis.blog where id in (1, 2, 3)
        <foreach collection="ids" item="id" open=" id in (" close=")" separator=",">
            #{id}
        </foreach>-->
    <select id="queryBlogForeach" parameterType="map" resultType="blog">
        select * from mybatis.blog
        <where>
            <foreach collection="ids" item="id" open=" id in (" close=")" separator=",">
                #{id}
            </foreach>
        </where>
    </select>
```



### SQL片段

有时候我们可能会将一些公共的部分抽取出来方便复用。 

1. 使用SQL标签抽取公共部分。

   ```xml
   <sql id="if-title-author">
       <if test="title != null">
           and title = #{title}
       </if>
       <if test="author != null">
           and author = #{author}
       </if>
   </sql>
   ```

2. 在需要使用的地方使用include标签引用即可。

   ```xml
   <select id="queryBlogIF" parameterType="map" resultType="blog">
           select * from mybatis.blog
           <where>
               <include refid="if-title-author"/>
           </where>
       </select>
   ```

   

3. 注意事项：

   1. 最好基于单表来定义SQL片段
   2. 不要存在where标签

### 总结

**动态SQL就是在拼接SQL语句，我们只要保证SQL的正确性，按照SQL的格式去排列组合就可以了。**

**建议：**

- ​	先在MySQL中写出完整的SQL，再对应的去修改成为我们的动态SQL实现通用即可。

