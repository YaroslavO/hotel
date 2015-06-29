package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.config.AbstractDataBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 * Created by gdpdp on 29.06.2015.
 */

public class AdminControllerTest extends AbstractDataBaseTest {

    @InjectMocks
    private AdminController controller;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Rollback(false)
    @Test
    public void testSomeFunctionController() throws Exception {
        mockMvc.perform(get("/admin/rooms/new")).andDo(print());
    }
}