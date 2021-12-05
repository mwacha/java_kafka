package tk.mwacha.http.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.producer.topic}")
    private String topic;

    public void send(String message) {
        log.info("message sent: {}", message);
        kafkaTemplate.send(topic, message);
    }
}
