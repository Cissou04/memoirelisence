package memoire.com.memoirelisence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MemoirelisenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoirelisenceApplication.class, args);
	}

}
