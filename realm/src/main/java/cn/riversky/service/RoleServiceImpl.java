package cn.riversky.service;

import cn.riversky.dao.RoleDao;
import cn.riversky.dao.RoleDaoImpl;
import cn.riversky.entity.Role;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao=new RoleDaoImpl();
    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }
    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId, permissionIds);
    }

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId, permissionIds);
    }
}
