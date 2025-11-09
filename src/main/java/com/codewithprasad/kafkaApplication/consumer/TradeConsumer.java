package com.codewithprasad.kafkaApplication.consumer;

import com.codewithprasad.kafkaApplication.dto.TradeEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class TradeConsumer {

    // Simple in-memory dedup (for demo). In prod use DB/Redis with TTL.
    private final ConcurrentHashMap<String, Boolean> processed = new ConcurrentHashMap<>();

    @KafkaListener(topics = "${app.topic.trades:trades}", groupId = "demo-group")
    public void consume(TradeEvent event, Acknowledgment ack) {
        String id = event.getTradeId();
        // idempotency: check if processed
        if (processed.putIfAbsent(id, Boolean.TRUE) == null) {
            // process the event
            System.out.println("Processing " + id + " : " + event.getSymbol());
            // after successful processing, ack the offset
            ack.acknowledge();
        } else {
            System.out.println("Duplicate ignored: " + id);
            // still acknowledge; do not reprocess
            ack.acknowledge();
        }
    }
}
