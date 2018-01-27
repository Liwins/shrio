package cn.riversky.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/23.
 */
public class LoginController {
    public void login(String configFile,String username,String pwd){
        Factory<SecurityManager> factory=new IniSecurityManagerFactory(configFile);
        SecurityManager manager=factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,pwd);
        subject.login(token);
    }
}
