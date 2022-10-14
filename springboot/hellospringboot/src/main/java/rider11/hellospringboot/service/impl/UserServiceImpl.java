package rider11.hellospringboot.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rider11.hellospringboot.bean.MyUser;
import rider11.hellospringboot.mapper.UserMapper;
import rider11.hellospringboot.service.UserService;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserMapper mapper;

    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public MyUser getByUserNameAndPassword(MyUser user) {
        MyUser u = this.mapper.getByUserNameAndPassword(user);
        return u;
    }

}
