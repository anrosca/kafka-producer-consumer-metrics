package dev.softice.kafka.domain;

import lombok.Builder;

@Builder
public record StockQuote(String id, String symbol, String exchange, String currency, String tradeValue) {
}
