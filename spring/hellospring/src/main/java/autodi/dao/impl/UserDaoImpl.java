package autodi.dao.impl;

import org.springframework.stereotype.Repository;

import autodi.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void print() {
        System.out.println("你好，这是" + UserDaoImpl.class.getName());
    }
}
