package mapper;

import java.util.List;

import po.User;

public interface UserMapper {
    public User selectUserOrderById(int id);
    public int insertOne(User user);
    public void insertMulti(List<User> users);
}
