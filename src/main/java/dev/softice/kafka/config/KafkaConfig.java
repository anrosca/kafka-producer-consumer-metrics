package dev.softice.kafka.config;

import dev.softice.kafka.domain.StockQuote;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.MicrometerProducerListener;
import org.springframework.kafka.core.ProducerFactory;

@Configuration(proxyBeanMethods = false)
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, StockQuote> producerFactory(KafkaProperties properties, MeterRegistry meterRegistry) {
        ProducerFactory<String, StockQuote> producerFactory = new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
        producerFactory.addListener(new MicrometerProducerListener<>(meterRegistry));
        return producerFactory;
    }

    @Bean
    public KafkaTemplate<String, StockQuote> kafkaTemplate(ProducerFactory<String, StockQuote> producerFactory) {
        KafkaTemplate<String, StockQuote> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        return new KafkaTemplate<>(producerFactory);
    }
}
