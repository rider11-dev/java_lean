package rider11.hellospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan // 扫描声明的 Servlet、Filter 和 Listener
@EnableAsync(proxyTargetClass = true)
public class HellospringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellospringbootApplication.class, args);
	}

}
