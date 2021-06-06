package com.rules.engine.mvel;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

@Getter
@NonNull
public class FactInput {
    private int orderId;
    private String deliveryTime;
    private Integer deliveryTimeInMillis;
    public int destinationDistance;
    private char hasRefrigerator;
    private CourierOutput cout;

    public FactInput(int orderId, String deliveryTime, int destinationDistance, char hasRefrigerator) {
        this.orderId = orderId;
        this.deliveryTime = deliveryTime;
        this.destinationDistance = destinationDistance;
        this.hasRefrigerator = hasRefrigerator;
        this.deliveryTimeInMillis = getTimeAsLocalTime(deliveryTime).get(ChronoField.MILLI_OF_DAY);
    }

    private LocalTime getTimeAsLocalTime(String time) {
        final DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.parse(time, format);
    }

    public void setCout(CourierOutput cout) {
        this.cout = cout;
    }
}


