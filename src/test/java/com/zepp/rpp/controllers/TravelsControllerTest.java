package com.zepp.rpp.controllers;

import com.zepp.rpp.domains.Travels;
import com.zepp.rpp.services.TravelsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class TravelsControllerTest {

    @Mock
    TravelsService travelsService;

    @Mock
    Model model;

    TravelsController travelsController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        travelsController = new TravelsController(travelsService);
    }

    @Test
    public void listTravels() throws  Exception{
        List<Travels> travelsList = new ArrayList<>();
        travelsList.add(new Travels());
        travelsList.add(new Travels());
        when(travelsService.findAll()).thenReturn(travelsList);

        ArgumentCaptor<List<Travels>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        String viewName = travelsController.listTravels(model);
        assertEquals("travels/travels", viewName);
        verify(travelsService, times(1)).findAll();
        // anySet(), anyList() u zavisnosti šta je model
        verify(model, times(1)).addAttribute(eq("travels"), argumentCaptor.capture());

        List<Travels> listOfController = argumentCaptor.getValue();
        assertEquals(2, listOfController.size());
    }

    @Test
    public void testMockMvc() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(travelsController).build();
        mockMvc.perform(get("/travelslist")).andExpect(status().isOk()).andExpect(view().name("travels/travels"));
    }
}