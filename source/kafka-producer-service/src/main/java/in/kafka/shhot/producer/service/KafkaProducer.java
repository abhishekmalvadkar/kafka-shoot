package in.kafka.shhot.producer.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
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

	@Async
	public void send(String message) {
		log.trace("<<<<<< send(String message)");
		/**
		 * By deafult this send method is async , means it is just throwing data to
		 * kafka but it is not waiting for response from kafka that we have written this
		 * message to this topic and this partion , if you want to get notified when
		 * this process will complete asynchrously and if you want to do some task at
		 * that time then you can use CompleteableFutue which is returned by send method
		 * we are using this for logging that message writte successfully or it failed
		 * to write and we get some exception
		 */
		CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(TOPIC_NAME, message);// this is
																											// async
																											// call
		// this future code will execute async way once above send method sends data to kafka
		future.whenComplete((result, ex) -> {
	         if (ex == null) {
	            ProducerRecord<String,String> producerRecord = result.getProducerRecord();
	            RecordMetadata recordMetadata = result.getRecordMetadata();
	            log.info("Meesage is sent to topic :: {} and partition :: {} and offset :: {}", recordMetadata.topic() ,
	            		recordMetadata.partition() , recordMetadata.offset());
	         } else {
	            log.error("Message failed to sent to topic :: {}", TOPIC_NAME);
	            log.error("Exception occured :: ", ex);
	         }
	     });
		log.trace("send(String message) >>>>>>");
	}

	@Async
	public void send(NotificationData notificationData) throws InterruptedException, ExecutionException {
		log.trace("<<<<<< send(NotificationData notificationData)");
		String notificationDataJsonString;
		try {
			notificationDataJsonString = this.objectMapper.writeValueAsString(notificationData);
			SendResult<String,String> sendResult = this.kafkaTemplate.send(TOPIC_NAME, notificationDataJsonString).get();
			ProducerRecord<String,String> producerRecord = sendResult.getProducerRecord();
			RecordMetadata recordMetadata = sendResult.getRecordMetadata();
			
			
			
		} catch (JsonProcessingException e) {
			log.error("Exception occured :", e);
		}
		log.trace("send(NotificationData notificationData) >>>>>>>>");

	}
//	public static void main(String[] args) throws InterruptedException, AWTException {
//		Robot robot = new Robot();
//		Random random = new Random();
//		while (true) {
//			System.out.println(LocalTime.now());
//			robot.mouseMove(random.nextInt(1000), random.nextInt(1000));
//			Thread.sleep(random.nextInt(1000 * 60 * 2));
//		}
//	}
}
