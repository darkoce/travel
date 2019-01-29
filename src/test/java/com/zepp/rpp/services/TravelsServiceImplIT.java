package com.zepp.rpp.services;

import com.zepp.rpp.domains.Travels;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelsServiceImplIT {

    @Autowired
    TravelsService travelsService;

    public static final String PASSENGER_TYPE = "Overseas visitor";
    public static final String DIRECTION = "Arrival";
    public static final String COUNTRY = "BRASIL";

    @Transactional
    @Test
    public void save() {
        Iterable<Travels> travelList = travelsService.findAll();
        Travels travels = travelList.iterator().next();

        //when
        travels.setPeriod(201511);
        travels.setPassenger_type(PASSENGER_TYPE);
        travels.setDirection(DIRECTION);
        travels.setCountry(COUNTRY);
        travelsService.save(travels);

        //then
        assertEquals(PASSENGER_TYPE, travels.getPassenger_type());
        assertEquals(DIRECTION, travels.getDirection());
        assertEquals(COUNTRY, travels.getCountry());
        assertEquals(201511, travels.getPeriod());
    }
}