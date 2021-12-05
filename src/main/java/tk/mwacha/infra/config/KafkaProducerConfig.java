package tk.mwacha.infra.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import tk.mwacha.service.CryptoService;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final ProducerFactory<Integer, String> producerFactory;

    private final CryptoService cryptoService;

    public Map<String, Object> producerConfig() {
        Map<String, Object> producerConfig = new HashMap<>(producerFactory.getConfigurationProperties());
        decryptAndAddToConsumerConfig(producerConfig, SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG);
        decryptAndAddToConsumerConfig(producerConfig, SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG);
        decryptAndAddToConsumerConfig(producerConfig, SslConfigs.SSL_KEY_PASSWORD_CONFIG);
        return producerConfig;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfig()));
    }

    private void decryptAndAddToConsumerConfig(Map<String, Object> config, String property) {
        config.compute(property, (k, v) -> cryptoService.decrypt((String) v));
    }
}
