package in.kafka.shhot.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.kafka.shhot.producer.dto.NotificationData;
import in.kafka.shhot.producer.service.KafkaProducer;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KafkaProducerController {
	
	private final KafkaProducer producer;

	@GetMapping("/produce/{data}")
	public String kafkaProducerTest(@PathVariable("data") String data) {
		this.producer.send(data);
		return "Data sent to kafka succesfully";
	}
	
	@PostMapping("/produce")
	public String kafkaProducerTest(@RequestBody NotificationData notificationData) {
		this.producer.send(notificationData);
		return "Enail sent to kafka";
	}
	
}
