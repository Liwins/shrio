package cn.riversky.demo;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public class UserRealmTest extends BaseTest {
    @Test
    public void testLoginSuccess(){
        login("classpath:shiro.ini",u1.getUsername(),password);
        System.out.println(u1);
        //判断是否通过认证
        System.out.println(subject().isAuthenticated());
    }
    @Test(expected = UnknownAccountException.class)
    public void testLoginFailWithUnknownUsername() {
        login("classpath:shiro.ini", u1.getUsername() + "1", password);
    }
    @Test(expected = IncorrectCredentialsException.class)
    public void testLoginFailWithErrorPassowrd() {
        login("classpath:shiro.ini", u1.getUsername(), password + "1");
    }
    @Test(expected = LockedAccountException.class)
    public void testLoginFailWithLocked() {
        login("classpath:shiro.ini", u4.getUsername(), password + "1");
    }
    @Test(expected = ExcessiveAttemptsException.class)
    public void testLoginFailWithLimitRetryCount() {
        for (int i = 0; i <= 5; i++) {
            try {
                login("classpath:shiro.ini",u3.getUsername(),password+"1");
            }catch (Exception e){

            }
        }
        login("classpath:shiro.ini",u3.getUsername(),password+"1");
    }
    @Test
    public void testHasRole() {
        login("classpath:shiro.ini", u1.getUsername(), password );
        System.out.println(subject().hasRole("admin"));
    }
    @Test
    public void testNoRole() {
        login("classpath:shiro.ini", u2.getUsername(), password);
        System.out.println(subject().hasRole("admin"));
    }
    @Test
    public void testHasPermission() {
        login("classpath:shiro.ini", u1.getUsername(), password);
        Assert.assertTrue(subject().isPermittedAll("user:create", "menu:update"));
    }

    @Test
    public void testNoPermission() {
        login("classpath:shiro.ini", u2.getUsername(), password);
        Assert.assertFalse(subject().isPermitted("user:create"));
    }
}
