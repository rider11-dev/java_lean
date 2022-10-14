package rider11.hellospringboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("rider11.hellospringboot.mapper")
public class MybatisPlusConfig {
    
}
