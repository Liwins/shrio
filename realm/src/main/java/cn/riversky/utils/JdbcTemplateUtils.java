package cn.riversky.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public class JdbcTemplateUtils {
    private static JdbcTemplate jdbcTemplate;

    /**
     * 单例模式懒汉室
     * @return
     */
    public static JdbcTemplate jdbcTemplate(){
        if(jdbcTemplate==null){
            jdbcTemplate=createJdbcTemplate();
        }
        return  jdbcTemplate;
    }
    private static JdbcTemplate createJdbcTemplate(){
        DruidDataSource ds=new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro");
        ds.setUsername("root");
        ds.setPassword("riversky");
        return new JdbcTemplate(ds);
    }
}
