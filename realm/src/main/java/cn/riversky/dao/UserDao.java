package cn.riversky.dao;

import cn.riversky.entity.User;

import java.util.Set;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public interface UserDao {
    User createUser(User user);
    void updateUser(User user);
    void deleteUser(Long userId);
    void correlationRoles(Long userId,Long... roleIds);
    void uncorrelationRoles(Long userId,Long... roleids);
    User findOne(Long userId);
    User findByUsername(String username);
    Set<String> findRoles(String username);
    Set<String> findPermissions(String username);
}
