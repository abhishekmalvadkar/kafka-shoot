package in.kafka.shhot.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KafkaProducerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerServiceApplication.class, args);
	}
	
	

}
