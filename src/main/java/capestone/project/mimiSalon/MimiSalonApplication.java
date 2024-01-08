package capestone.project.mimiSalon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@SpringBootApplication
public class MimiSalonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MimiSalonApplication.class, args);
		System.out.println("-------------------------------------------I am working-------------------------------------------------");
	}
}

