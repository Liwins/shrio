package cn.riversky;

import cn.riversky.controller.LoginController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/22.
 */
public class TestChar2 {
    @Test
    public void test(){
//        1,创建工厂
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
//        2利用工厂获取SecurityUtils 工具类应该是使用的核心，这里应该使用了将接口封装进去，然后做了代理
        SecurityManager manager=factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
//        3使用代理工具类获取Subject
        Subject subject=SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken("zhang","123");
        try{
            //4登录，身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            //5身份验证失败
        }
//        6退出
        subject.logout();
    }
    @Test
    public void login(){
        LoginController loginController=new LoginController();
        loginController.login("classpath:shiro-realm.ini","zhang","123");
        Subject subject=SecurityUtils.getSubject();
        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection=subject.getPrincipals();

        System.out.println(principalCollection.asList().size());
    }

    /**
     * 测试 AllSuccessfulStrategy 失败：
     */
    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        LoginController loginController=new LoginController();
        loginController.login("classpath:shiro-realm.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
    }

}
