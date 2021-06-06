package com.rules.engine.bo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourierTest {

    private Courier courier1;
    private Courier courier2;
    private Courier courier3;

    @BeforeEach
    public void init() {
        System.out.println("***** Begin Of CourierTest *****");
        courier1 = new Courier(1, "Bobby", "09:00 AM", "01:00 PM", 5, 'Y', "1.75");
        courier2 = new Courier(2, "Martin", "09:00 AM", "05:00 PM", 3, 'N', "1.50");
        courier3 = new Courier(3, "Geoff", "10:00 AM", "04:00 PM", 5, 'Y', "2.00");
    }

    @Test
    public void courierConstructorTest() {
       System.out.println(courier1);
       System.out.println(courier2);
       System.out.println(courier3);
    }

    @Test
    public void compareBigDecimal() {
        BigDecimal num1 = new BigDecimal(10.12);
        BigDecimal num2 = new BigDecimal(10.1234);
        assertEquals(-1, num1.compareTo(num2));
        assertEquals(1, num2.compareTo(num1));
        assertEquals(0, num1.compareTo(num1));
    }

    @AfterEach
    public void close() {
        System.out.println("***** End Of CourierTest *****");
    }

}
