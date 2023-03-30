package in.kafka.shhot.consumer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotificationEventConsumer {
	
	
	@KafkaListener(topics = {"test-topic"})
	public void onMessage(ConsumerRecord<String, String> consumerRecord) {
		log.info("Consumer record :: {}" , consumerRecord);
	}

}
