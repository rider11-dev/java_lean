package rider11.hellospringboot.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class MyUser {
    private Integer id;
    private String userId;
    private String userName;
    private String password;
    private String email;
}
