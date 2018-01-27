package cn.riversky.controller;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/24.
 */
public class MyReamlm extends AuthorizingRealm {
    /**
     * 添加角色
     * Role自己设计
     * 思路：应该通过Dao查询到role,并且通过Role.getRoleName()进行角色查询
     * @param username
     * @param info
     */
    private void addRole(String username, SimpleAuthorizationInfo info) {
//        List<Role> roles = roleDao.findByUser(username);
        List<String> roles = new ArrayList<String>();
        roles.add("role1");
        roles.add("role2");
        if(roles!=null&&roles.size()>0){
            for (String role : roles) {
                info.addRole(role);
            }
        }
    }

    /**
     * 添加权限
     * 资源类Permission需要自己设计
     * @param username
     * @param info
     * @return
     */
    private SimpleAuthorizationInfo addPermission(String username,SimpleAuthorizationInfo info) {
//        List<Permission> permissions = permissionDao.findPermissionByName(username);
        List<String> permissions = new ArrayList<String>();
        permissions.add("+user2+10");
        permissions.add("user3:*");
        //具有user1的修改和查看权限
        permissions.add("+user1+10");
        for (String permission : permissions) {
            info.addStringPermission(permission);//添加权限
//            info.addStringPermission(permission.getUrl());//添加权限
        }
        return info;
    }
    //认证模块
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username= (String) token.getPrincipal();
        String password=new String((char[])token.getCredentials());
        //用户名错误zhang--应该通过Service层进行校验
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();
        }
        //认证错误--应该通过Service层进行校验
        if(!"123".equals(password)){

            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
    //授权模块
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username= (String) principals.fromRealm(getName()).iterator().next();
        if(!StringUtils.isEmpty(username)){
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            addRole(username,authorizationInfo);
            addPermission(username,authorizationInfo);
            return authorizationInfo;
        }
        return null;
    }
}
