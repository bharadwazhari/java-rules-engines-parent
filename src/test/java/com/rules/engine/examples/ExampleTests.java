package com.rules.engine.examples;

import com.rules.engine.mvel.FactInput;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Locale;
import org.joda.time.Interval;
import org.joda.time.Period;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleTests {

    @BeforeEach
    public void init() {
        System.out.println("***** Begin Of ExampleTests *****");
    }

    @Test
    public void testDuration() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime time1 = LocalTime.parse("09:30 AM", format);
        LocalTime time2 = LocalTime.parse("01:00 PM", format);
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd/M/yyyy hh:mm a");
        Calendar cal1 = Calendar.getInstance();
        long milli1 = time1.get(ChronoField.MILLI_OF_DAY);
        long milli2 = time2.get(ChronoField.MILLI_OF_DAY);
        Calendar cal2 = Calendar.getInstance();

        Duration dur = Duration.between(time2, time1);
        long seconds =  (milli2 - milli1) / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + ":" + hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
        System.out.println("Durations => " + time);

        Interval interval =
                new Interval(milli1, milli2);
        Period period = interval.toPeriod();
        System.out.printf(
                "%d years, %d months, %d days, %d hours, %d minutes, %d seconds%n",
                period.getYears(), period.getMonths(), period.getDays(),
                period.getHours(), period.getMinutes(), period.getSeconds());
    }

    @Test
    public void bigDecimalTest() {
        BigDecimal charge = new BigDecimal(20).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN);
        java.math.BigDecimal totalCost = new java.math.BigDecimal(1.75).multiply(new java.math.BigDecimal(5)).setScale(2, java.math.RoundingMode.HALF_EVEN);
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(totalCost));
    }

    @Test
    public void testRangeOfNumbers() {
        final Range<Integer> range = Range.between(Integer.valueOf(1), Integer.valueOf(10));
        assertTrue(range.contains(1));
        assertFalse(range.contains(11));
    }

    @Test
    public void testRangeOfTimePeriod() {
        java.time.format.DateTimeFormatter format = java.time.format.DateTimeFormatter.ofPattern("hh:mm a");
        java.time.LocalTime startTime = java.time.LocalTime.parse("09:00 AM", format);
        java.time.LocalTime endTime = java.time.LocalTime.parse("01:00 PM", format);
        java.lang.Integer stime = java.lang.Integer.valueOf(startTime.get(java.time.temporal.ChronoField.MILLI_OF_DAY));
        java.lang.Integer etime = java.lang.Integer.valueOf(endTime.get(java.time.temporal.ChronoField.MILLI_OF_DAY));
        org.apache.commons.lang3.Range<java.lang.Integer> range = org.apache.commons.lang3.Range.between(stime, etime);
        System.out.println(range);
        com.rules.engine.mvel.FactInput input = new com.rules.engine.mvel.FactInput(101, "10:30 AM", 3, 'Y');
        System.out.println(range.contains(input.getDeliveryTimeInMillis()));
        boolean isInBetween = stime <= input.getDeliveryTimeInMillis() && input.getDeliveryTimeInMillis() <= etime;
        System.out.println(isInBetween);
    }

    @Test
    public void calculations() {
        FactInput input = new FactInput(101, "10:30 AM", 3, 'Y');
    }

    @AfterEach
    public void close() {
        System.out.println("***** End Of ExampleTests *****");
    }

}
