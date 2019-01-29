package com.zepp.rpp.domains;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TravelsTest {

    Travels travels;

    @Before
    public void setUp(){
        travels = new Travels();
    }

    @Test
    public void getTravels_id() {
        int idValue = 49;
        travels.setTravels_id(idValue);

        assertEquals(idValue, travels.getTravels_id());
    }

    @Test
    public void getPeriod() {
        int period = 201812;
        travels.setPeriod(period);

        assertEquals(period, travels.getPeriod());
    }

    @Test
    public void getDirection() {
        String arrival = "Arrival";
        travels.setDirection(arrival);

        assertEquals(arrival, travels.getDirection());
    }
}