package rider11.hellospringboot.service;

import rider11.hellospringboot.bean.MyUser;

public interface UserService {
    MyUser getByUserNameAndPassword(MyUser user);
}
