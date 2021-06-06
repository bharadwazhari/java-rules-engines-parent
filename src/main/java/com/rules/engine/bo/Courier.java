package com.rules.engine.bo;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.Range;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

@Getter
@NonNull
public class Courier {
    private int id;
    private String name;
    private String stime;
    private String etime;
    private LocalTime startTime;
    private LocalTime endTime;
    private Duration duration;
    private Range<Integer> range;
    private int milesCapability;
    private char refrigerator;
    private BigDecimal chargePerMile;


    public Courier(int id, String name, String stime, String etime, int milesCapability, char refrigerator, String chargePerMile) {
        this.id = id;
        this.name = name;
        this.stime = stime;
        this.etime = etime;
        this.milesCapability = milesCapability;
        this.refrigerator = refrigerator;
        this.chargePerMile = new BigDecimal(chargePerMile).setScale(2, RoundingMode.HALF_EVEN);
        this.startTime = getTimeAsLocalTime(stime);
        this.endTime = getTimeAsLocalTime(etime);
        this.range = Range.between(startTime.get(ChronoField.MILLI_OF_DAY), endTime.get(ChronoField.MILLI_OF_DAY));
    }

    private LocalTime getTimeAsLocalTime(String time) {
        final DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.parse(time, format);

    }
}
