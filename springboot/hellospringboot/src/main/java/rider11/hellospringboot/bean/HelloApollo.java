package rider11.hellospringboot.bean;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("helloapollo")
public class HelloApollo {
    private String name;
    private Integer age;
    private List<String> hobits;
}
