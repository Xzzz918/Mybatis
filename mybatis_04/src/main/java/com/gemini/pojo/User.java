package com.gemini.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author gemini
 * Created in  2021/4/27 19:17
 * 实体类
 * Lombok的使用步骤:
 * 安装插件
 * 导入jar包
 * 添加注解
 */
//@Data:生成无参构造,getter,setter,toString,HashCode,equals
@Data
//@AllArgsConstructor:有参构造,但是这时候无参构造会消失
@AllArgsConstructor
//@NoArgsConstructor:无参构造,补加无参构造
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;
}
