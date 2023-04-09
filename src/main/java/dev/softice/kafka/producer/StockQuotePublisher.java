package dev.softice.kafka.producer;

import dev.softice.kafka.config.KafkaTopics;
import dev.softice.kafka.domain.StockQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockQuotePublisher {

    private final KafkaTemplate<String, StockQuote> stockQuotePublisher;

    public StockQuotePublisher(KafkaTemplate<String, StockQuote> stockQuotePublisher) {
        this.stockQuotePublisher = stockQuotePublisher;
    }

    public void publish(StockQuote stockQuote) {
        log.info("Publishing stock quote: {}", stockQuote);
        stockQuotePublisher.send(KafkaTopics.STOCK_QUOTES_TOPIC, stockQuote.id(), stockQuote);
    }
}
