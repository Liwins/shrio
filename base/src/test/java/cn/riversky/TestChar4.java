package cn.riversky;

import cn.riversky.controller.LoginController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/24.
 */
public class TestChar4 {
    private LoginController loginController;
    @Before
    public void init(){
        loginController=new LoginController();
    }
    @Test
    public void shou(){
        loginController.login("classpath:shiro-realm.ini","zhang","123");
        System.out.println();
        Subject subject= SecurityUtils.getSubject();
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:create"));
//        System.out.println(subject.isPermitted("user+2"));
//判断拥有权限：user:update and user:delete

        System.out.println(subject.isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        System.out.println(subject.isPermitted("user:view"));
    }
    @Test
    public void demo(){
        loginController.login("classpath:shirio-ream.ini","zhang","123");
        Subject subject= SecurityUtils.getSubject();
        System.out.println(subject.hasRole("role1"));
        //基于角色的验证方式
        System.out.println(subject.isPermitted("menu:create:1"));
        //基于资源的验证方式
        System.out.println(subject.isPermitted("user3:view"));
        System.out.println(subject.isPermitted("+user2+10"));//新增和查看权限
    }
}
