package cn.riversky.controller;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/24.
 */
public class BitAndWildPermissionResolver implements PermissionResolver {
    @Override
    public Permission resolvePermission(String s) {
        if(s.startsWith("+")) {
            return new BitPermission(s);
        }
        return new WildcardPermission(s);
    }
}
