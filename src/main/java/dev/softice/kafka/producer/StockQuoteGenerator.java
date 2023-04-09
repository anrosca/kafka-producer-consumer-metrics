package dev.softice.kafka.producer;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import dev.softice.kafka.domain.StockQuote;
import org.springframework.stereotype.Component;

@Component
public class StockQuoteGenerator {

    private static final String[] EXCHANGES = {"NYSE", "NSDQ"};
    private static final String[] STOCK_SYMBOLS = {"DAVA", "AAPL", "NFLX", "META", "GOGL", "TSLA", "AMZN"};

    public StockQuote generate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return StockQuote.builder()
                         .id(UUID.randomUUID().toString())
                         .currency("EUR")
                         .exchange(EXCHANGES[random.nextInt(EXCHANGES.length)])
                         .symbol(STOCK_SYMBOLS[random.nextInt(STOCK_SYMBOLS.length)])
                         .tradeValue(BigDecimal.valueOf(random.nextInt(60, 1000)).toString())
                         .build();
    }
}
