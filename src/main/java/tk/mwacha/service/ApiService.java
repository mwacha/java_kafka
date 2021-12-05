package tk.mwacha.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApiService {

    @Async
    public void startTask() {
        process();
    }

    private void process()  {
        for(int i = 0; i < 10; i++) {
            log.info("THREAD {}", i);
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            log.info("FINISH");
        }
    }
}
