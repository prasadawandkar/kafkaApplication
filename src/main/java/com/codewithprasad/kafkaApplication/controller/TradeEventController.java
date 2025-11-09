package com.codewithprasad.kafkaApplication.controller;


import com.codewithprasad.kafkaApplication.dto.TradeEvent;
import com.codewithprasad.kafkaApplication.producer.TradeProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trades")
public class TradeEventController {

    private final TradeProducer tradeProducer;

    public TradeEventController(TradeProducer tradeProducer) {
        this.tradeProducer = tradeProducer;
    }

    @PostMapping("/publish")
    public String sendTradeEvent(@RequestBody TradeEvent tradeEvent){
        tradeProducer.send(tradeEvent);
        return "Trade published successfully: " + tradeEvent.getTradeId();
    }
}
