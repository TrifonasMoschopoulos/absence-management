package gr.techpro.absence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
@EnableJpaAuditing
public class AbsenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbsenceApplication.class, args);
	}

}
