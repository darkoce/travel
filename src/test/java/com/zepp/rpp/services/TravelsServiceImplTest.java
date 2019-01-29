package com.zepp.rpp.services;

import com.zepp.rpp.domains.Travels;
import com.zepp.rpp.repositories.TravelsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TravelsServiceImplTest {

    TravelsServiceImpl travelsService;

    @Mock
    TravelsRepository travelsRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        travelsService = new TravelsServiceImpl(travelsRepository);
    }

    @Test
    public void save() {
    }

    @Test
    public void findAll() throws Exception{
        List<Travels> travelsList = travelsService.findAll();
        travelsList.add(new Travels());

        when(travelsService.findAll()).thenReturn(travelsList);

        assertEquals(travelsList.size(), 1);
        // da vidimo da li se metoda findAll() poziva 1
        verify(travelsRepository, times(1)).findAll();
    }

    @Test
    public void findOne() {
    }
}