package cn.riversky.dao;

import cn.riversky.entity.Role;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public interface  RoleDao {
    Role createRole(Role role);
    void deleteRole(Long roleId);
    void correlationPermissions(Long roleId,Long... permissionIds);
    void uncorrelationPermissions(Long roleId,Long... permissionIds);
}
