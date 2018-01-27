package cn.riversky.controller;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * 通过该方式可以实现粗粒度的基于角色的权限赋予
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/24.
 */
public class MyRolePermissionResolver implements RolePermissionResolver{

    @Override
    public Collection<Permission> resolvePermissionsInRole(String s) {
        //这里其实应该从dao中进行角色查询，然后添加权限的。这里进行了写死的权限赋予
        if("role1".equals(s)) {
            return Arrays.asList((Permission)new WildcardPermission("menu:*"));
        }
        return null;
    }
}
