package cn.riversky.dao;

import cn.riversky.entity.Permission;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public interface PermissionDao {
    Permission createPermission(Permission permission);
    void deletePermission(Long permissionId);
}
