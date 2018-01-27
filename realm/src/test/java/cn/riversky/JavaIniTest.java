package cn.riversky;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public class JavaIniTest {
    @Test
    public void testiniJava(){
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        //---------------认证模块设置
        ModularRealmAuthenticator authenticator=new ModularRealmAuthenticator();
        //设置认证的策略，只要有一个realm通过，才算认证通过
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());

        //---------------设置授权模块
        ModularRealmAuthorizer authorizer=new ModularRealmAuthorizer();
        //设置默认的授权解析器（当有特殊需求的时候，我们可以自定义--就像上一篇中字节类解析其一样）
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        //这里可以设置角色解析器，需要用户实现RolePermissionResolver接口来进行设置
        //---------------设置Realm
        DruidDataSource ds=new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql:///shiro");
        ds.setUsername("root");
        ds.setPassword("riversky");
        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setDataSource(ds);
        //資源的解析必要的
        jdbcRealm.setPermissionsLookupEnabled(true);
        //-------------组件SecurityManager
        securityManager.setAuthenticator(authenticator);
        securityManager.setAuthorizer(authorizer);
        securityManager.setRealms(Arrays.asList((Realm)jdbcRealm));
        //-------------对外提供使用的SeurityUtils工具类，可以获取Subject门面类
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("zhang","123"));
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:create"));
        subject.logout();
    }
}
