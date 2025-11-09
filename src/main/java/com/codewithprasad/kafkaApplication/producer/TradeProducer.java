package com.codewithprasad.kafkaApplication.producer;

import com.codewithprasad.kafkaApplication.dto.TradeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class TradeProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final String topic;

    public TradeProducer(KafkaTemplate<String, Object> kafkaTemplate,
                         @Value("${app.topic.trades:trades}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(TradeEvent event) {
        kafkaTemplate.send(topic, event.getTradeId(), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        // log and optionally retry / handle
                        ex.printStackTrace();
                    } else {
                        System.out.println("Sent: " + event.getTradeId() + " to partition " + result.getRecordMetadata().partition());
                    }
                });
    }
}
