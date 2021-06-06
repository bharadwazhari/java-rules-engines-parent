package com.plainjava.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.graalvm.util.CollectionsUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CourierUtils {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");

    public static long getTimeInMillis(String time) {
        LocalTime localTime = LocalTime.parse(time, format);
        long milli = localTime.get(ChronoField.MILLI_OF_DAY);
        return milli;
    }

    public static Range<Long> getWorkingHoursRange(String stime, String etime) {
        Range<Long> wtime = Range.between(getTimeInMillis(stime), getTimeInMillis(etime));
        return wtime;
    }

    public static BigDecimal getDollarAmount(String dollar) {
        String damt = StringUtils.replace(dollar, "$", StringUtils.EMPTY);
        return new BigDecimal(damt).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static List<CourierDelivery> sortCourierDeliveryObjects(List<CourierDelivery> inlist){
        final List<CourierDelivery> sortedList = inlist.stream()
                .sorted(Comparator.comparing(CourierDelivery::getTotalCost))
                .collect(Collectors.toList());
        return sortedList;
    }

    public static String economicalCourier(List<CourierDelivery> inlist) {
        if(CollectionUtils.isEmpty(inlist)) return "NO COURIER SERVICE AVAILABLE";
        List<CourierDelivery> sortedByTotalPriceList = sortCourierDeliveryObjects(inlist);
        CourierDelivery top = sortedByTotalPriceList.get(0);
        return top.getRuleApplied();
    }

    public static String convertAnyObjectToJsonString(Object obj) {
        String json = "";
        try {
            json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


}
