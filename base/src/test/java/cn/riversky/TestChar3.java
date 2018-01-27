package cn.riversky;

import cn.riversky.controller.LoginController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/23.
 */
public class TestChar3 {
    private LoginController loginController;
    @Before
    public void init(){
        loginController=new LoginController();
    }
    @Test(expected = UnauthenticatedException.class)
    public void testHasRole(){
        loginController.login("classpath:shiro-realm.ini","zhang","123");
        Subject subject= SecurityUtils.getSubject();
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.hasRole("role2"));
        System.out.println(subject.hasRole("role3"));
    }
    @Test(expected = UnauthenticatedException.class)
    public void testHasRole2(){
        loginController.login("classpath:shiro-realm.ini","zhang","123");
        Subject subject= SecurityUtils.getSubject();
        subject.checkRole("role1");
        subject.checkRole("role2");
        subject.checkRole("role3");

    }
    @Test
    public void testIsPermitted() {
        loginController.login("classpath:shiro-realm.ini","zhang","123");
        Subject subject=SecurityUtils.getSubject();
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:create"));
//        System.out.println(subject.isPermitted("user+2"));
//判断拥有权限：user:update and user:delete

        System.out.println(subject.isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        System.out.println(subject.isPermitted("user:view"));
    }
}
