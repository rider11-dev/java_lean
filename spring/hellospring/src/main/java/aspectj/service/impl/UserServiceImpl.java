package aspectj.service.impl;

import org.springframework.stereotype.Service;

import aspectj.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(String userName) {
        System.out.println("AddUser:" + userName);
    }

    @Override
    public String getUser() {
        String userName = "李四";
        System.out.println("GetUser:" + userName);

        return userName;
    }

    @Override
    public void exception() {
        int i = 0;
        int j = 10;
        int k = j / i;

    }
}
