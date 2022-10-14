package hello.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import hello.model.User;

@Repository
public class UserDao {
    private static Map<String, User> users = null;
    static {
        users = new HashMap<String, User>();
        User user = new User();
        user.setUserId("1001");
        user.setUserName("Java用户");
        user.setPassword("123123");

        User user2 = new User();
        user2.setUserId("1002");
        user2.setUserName("admin");
        user2.setPassword("123123");

        users.put(user.getUserName(), user);
        users.put(user2.getUserName(), user2);

        // for (User u : users.values()) {
        //     System.out.println(u.toString());
        // }
    }

    public User getUserByName(String userName) {
        User u = users.get(userName);
        return u;
    }
}
