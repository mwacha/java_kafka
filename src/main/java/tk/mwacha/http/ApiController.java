package tk.mwacha.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mwacha.http.notification.KafkaProducer;
import tk.mwacha.service.ApiService;

@RestController("/api/v1")
@RequiredArgsConstructor
public class ApiController {

//    private final ApiService service;
//
//    @PostMapping
//    public void post() {
//        service.startTask();
//    }

    private final KafkaProducer producer;

    @PostMapping("/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        producer.send(message);
    }
}
