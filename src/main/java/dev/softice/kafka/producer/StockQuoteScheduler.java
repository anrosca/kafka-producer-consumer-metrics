package dev.softice.kafka.producer;

import dev.softice.kafka.domain.StockQuote;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockQuoteScheduler {

    private final StockQuotePublisher quotePublisher;
    private final StockQuoteGenerator stockQuoteGenerator;

    public StockQuoteScheduler(StockQuotePublisher quotePublisher, StockQuoteGenerator stockQuoteGenerator) {
        this.quotePublisher = quotePublisher;
        this.stockQuoteGenerator = stockQuoteGenerator;
    }

//    @Scheduled(fixedRate = 500)
//    public void tick() {
//        StockQuote stockQuote = stockQuoteGenerator.generate();
//        quotePublisher.publish(stockQuote);
//    }

    @PostConstruct
    private void init() {
        new Thread(() -> {
            try {
                while (true) {
                    quotePublisher.publish(stockQuoteGenerator.generate());
                }
            } catch (Exception e) {
                log.error("Kafka producer error", e);
            }
        }).start();
    }
}
