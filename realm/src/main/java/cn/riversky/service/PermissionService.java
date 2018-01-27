package cn.riversky.service;

import cn.riversky.entity.Permission;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
