package jdbc.dao;

import java.util.List;

import jdbc.entity.User;

public interface UserDao {
    int addUser(User user);

    int update(User user);

    int delete(User user);

    int count(User user);

    List<User> getList(User user);

    User getUser(User user);

    void batchAddUser(List<Object[]> batchArgs);
}
