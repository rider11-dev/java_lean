package rider11.hellospringboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

// https://baomidou.com/pages/223848/#tablename
@Data
@TableName("mp_user")
public class MpUser {
    @TableId
    private Long id;
    @TableField("name")
    private String name;
    private Integer age;
    private String email;
}
