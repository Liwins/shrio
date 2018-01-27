package cn.riversky.demo;

import cn.riversky.entity.Permission;
import cn.riversky.entity.Role;
import cn.riversky.entity.User;
import cn.riversky.service.*;
import cn.riversky.utils.JdbcTemplateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public class BaseTest {
    protected PermissionService permissionService=new PermissionServiceImpl();
    protected RoleService roleService=new RoleServiceImpl();
    protected UserService userService=new UserServiceImpl();
    protected String password="123";
    protected Permission p1;
    protected Permission p2;
    protected Permission p3;
    protected Role r1;
    protected Role r2;
    protected User u1;
    protected User u2;
    protected User u3;
    protected User u4;
    @Before
    public void setUp(){
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_permissions");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles_permissions");
        //新增权限
        p1=new Permission("user:create","用户模块新增",Boolean.TRUE);
        p2=new Permission("user:update","用户模块修改",Boolean.TRUE);
        p3=new Permission("menu:update","菜单模块修改",Boolean.TRUE);
        permissionService.createPermission(p1);
        permissionService.createPermission(p2);
        permissionService.createPermission(p3);
        //新增角色
        r1=new Role("admin","管理员",Boolean.TRUE);
        r2=new Role("user","用户管理",Boolean.TRUE);
        roleService.createRole(r1);
        roleService.createRole(r2);
        //关联角色-权限
        roleService.correlationPermissions(r1.getId(),p1.getId(),p2.getId(),p3.getId());
        roleService.correlationPermissions(r2.getId(),p2.getId(),p1.getId());

        //新增用户
        u1=new User("zhang",password);
        u2=new User("li",password);
        u3=new User("wu",password);
        u4=new User("wang",password);
        u4.setLocked(true);
        userService.createUser(u1);
        userService.createUser(u2);
        userService.createUser(u3);
        userService.createUser(u4);
        //关联用户与角色
        userService.correlationRoles(u1.getId(),r1.getId());

    }
    @After
    public void tearDown()throws Exception{
        //退出时请解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }
    protected void login(String configFile,String username,String password){
        Factory<SecurityManager> factory=new IniSecurityManagerFactory(configFile);
        SecurityManager manager=factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject=subject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        subject.login(token);
    }
    public Subject subject(){
        return SecurityUtils.getSubject();
    }
    @Test
    public void test(){
        login("classpath:shiro.ini","zhang","123");
        Subject subject=SecurityUtils.getSubject();
        System.out.println(subject.isPermitted("user:create"));
    }
}
