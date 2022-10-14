package rider11.hellospringboot.entity;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "self.user")
@ApiModel("用户信息")
public class User {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("兴趣爱好")
    private List<String> hobits;
}
