package mvc.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// ServletComponentScan 어노테이션을 붙이면 Spring이 알아서 서블릿을 찾아 실행시켜 준다.
@ServletComponentScan
@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}

}
