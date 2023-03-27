package in.kafka.shhot.producer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.kafka.shhot.producer.dto.NotificationData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

	private static final String TOPIC_NAME = "test-topic";
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	private final ObjectMapper objectMapper;

	public void send(String message) {
		this.kafkaTemplate.send(TOPIC_NAME, message);
	}
	
	public void send(NotificationData notificationData) {
		
		String notificationDataJsonString;
		try {
			notificationDataJsonString = this.objectMapper.writeValueAsString(notificationData);
			this.kafkaTemplate.send(TOPIC_NAME, notificationDataJsonString);
		} catch (JsonProcessingException e) {
			log.error("Exception occured :" , e);
		}
		
	}

}
