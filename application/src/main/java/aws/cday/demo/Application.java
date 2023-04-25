package aws.cday.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

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
			return "Hello 2022 Community Day!!";
		}
	}

}
