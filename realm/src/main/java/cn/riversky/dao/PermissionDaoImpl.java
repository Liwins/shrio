package cn.riversky.dao;

import cn.riversky.entity.Permission;
import cn.riversky.utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public class PermissionDaoImpl implements PermissionDao {
    private JdbcTemplate jdbcTemplate= JdbcTemplateUtils.jdbcTemplate();
    @Override
    public Permission createPermission(final Permission permission) {
        final String sql="insert into sys_permissions(permission, description, available) values(?,?,?)";
        GeneratedKeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstm=connection.prepareStatement(sql,new String[]{"id"});
                pstm.setString(1,permission.getPermission());
                pstm.setString(2,permission.getDescription());
                pstm.setBoolean(3,permission.getAvailable());
                return pstm;
            }
        },keyHolder);
        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }
    @Override
    public void deletePermission(Long permissionId) {
//首先把与permission关联的相关表的数据删掉
        String sql = "delete from sys_roles_permissions where permission_id=?";
        jdbcTemplate.update(sql, permissionId);

        sql = "delete from sys_permissions where id=?";
        jdbcTemplate.update(sql, permissionId);
    }
}
