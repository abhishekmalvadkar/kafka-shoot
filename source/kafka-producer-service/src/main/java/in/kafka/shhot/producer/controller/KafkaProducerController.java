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
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerController {
	
	private final KafkaProducer producer;

	@GetMapping("/produce/{data}")
	public String kafkaProducerTest(@PathVariable("data") String data) {
		log.trace("<<<<< kafkaProducerTest()");
		/*
		 * This send call will be synchronous by deafult, so to make this call as asynchronous I have added
		 * @Async on this send method
		 */
		this.producer.send(data); // now this will be async call
		log.trace("kafkaProducerTest() >>>>>");
		return "Data sent to kafka succesfully";
	}
	
	@PostMapping("/produce")
	public String kafkaProducerTest(@RequestBody NotificationData notificationData) {
		/*
		 * This send call will be synchronous by deafult, so to make this call as asynchronous I have added
		 * @Async on this send method
		 */
		this.producer.send(notificationData); // now this will be async call
		return "Enail sent to kafka";
	}
	
}
