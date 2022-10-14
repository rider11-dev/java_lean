package rider11.hellospringboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("shiro_account")
public class ShiroAccount {
    private Integer id;
    private String username;
    private String password;
    private String perms;
    private String role;
}
