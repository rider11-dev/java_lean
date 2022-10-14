package autodi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import autodi.dao.UserDao;
import autodi.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void out() {
        userDao.print();
        System.out.println("你好，这是:" + UserServiceImpl.class.getName());
    }
}
