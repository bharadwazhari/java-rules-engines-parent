package com.plainjava.rules;

import lombok.*;
import org.apache.commons.lang3.Range;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

@Getter
@NonNull
@AllArgsConstructor
public class InputOrder {
    private int orderId;
    private String deliveryTime;
    public int destinationDistance;
    private char refrigeratorRequired;
}
