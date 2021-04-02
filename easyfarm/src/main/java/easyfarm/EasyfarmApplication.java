package easyfarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class EasyfarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyfarmApplication.class, args);
	}

	
}
