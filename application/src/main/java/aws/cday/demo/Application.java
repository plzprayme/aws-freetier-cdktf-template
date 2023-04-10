package aws.cday.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {


	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RestController
	public static class Controller {

		@GetMapping
		public ResponseEntity<Void> health() {
			return ResponseEntity.ok()
					.build();
		}

		@GetMapping("/hello")
		public String hell() {
			log.info("Hello World! | " + LocalDateTime.now());
			return "Hello 2022 Community Day!!";
		}
	}

}
