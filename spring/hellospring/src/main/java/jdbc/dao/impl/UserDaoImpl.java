package jdbc.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jdbc.dao.UserDao;
import jdbc.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbc;

    // private NamedParameterJdbcTemplate
    public UserDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into `user`(user_name,`status`)values(?,?);";
        int count = jdbc.update(sql, user.getUserName(), user.getStatus());
        return count;
    }

    @Override
    public int update(User user) {
        String sql = "update `user` set `status`=? where user_name=?;";
        int count = jdbc.update(sql, user.getStatus(), user.getUserName());
        return count;
    }

    @Override
    public int delete(User user) {
        String sql = "delete from `user` where user_name=?";
        int count = jdbc.update(sql, user.getUserName());
        return count;
    }

    @Override
    public int count(User user) {
        String sql = "select count(1) from `user` where `status`=?";
        return jdbc.queryForObject(sql, Integer.class, user.getStatus());
    }

    @Override
    public List<User> getList(User user) {
        String sql = "select * from `user` where `status`=?;";
        return jdbc.query(sql, new BeanPropertyRowMapper<User>(User.class), user.getStatus());
    }

    @Override
    public User getUser(User user) {
        String sql = "select * from `user` where user_id=?;";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserId());
    }

    @Override
    public void batchAddUser(List<Object[]> batchArgs) {
        String sql = "insert into `user`(user_name,`status`)values(?,?);";
        jdbc.batchUpdate(sql, batchArgs);
    }

}
