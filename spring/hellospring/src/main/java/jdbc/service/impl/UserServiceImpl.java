package jdbc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jdbc.dao.UserDao;
import jdbc.entity.User;
import jdbc.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public int deleteUser(User user) {
        return userDao.delete(user);
    }

    @Override
    public int countUser(User user) {
        return userDao.count(user);
    }

    @Override
    public List<User> getUserList(User user) {
        return userDao.getList(user);
    }

    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }

    @Override
    public void batchAddUser(List<Object[]> batchArgs) {
        userDao.batchAddUser(batchArgs);
    }
}
