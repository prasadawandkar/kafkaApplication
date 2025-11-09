package com.codewithprasad.kafkaApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeEvent {

    private String tradeId;
    private String symbol;
    private double qty;
    private double price;
    private long timestamp;

}
