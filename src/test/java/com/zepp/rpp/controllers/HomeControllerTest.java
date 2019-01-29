package com.zepp.rpp.controllers;

import com.zepp.rpp.domains.Notice;
import com.zepp.rpp.services.notice.NoticeService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest {

    @Mock
    NoticeService noticeService;

    @Mock
    Model model;


    HomeController homeController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        homeController = new HomeController(noticeService);
    }

    @Test
    public void about() {
        String viewName = homeController.about();
        assertEquals("common/about", viewName);
    }

}