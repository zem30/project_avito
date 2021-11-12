package com.amr.project.webapp.rest_controller;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getOrder() throws Exception {
        mockMvc.perform(get("http://localhost:8888/api/order/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user1_firstname", authorities = {"USER"})
    public void testCreateOrder() throws Exception {
        mockMvc.perform(post("http://localhost:8888/api/order"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user1_firstname", authorities = {"USER"})
    public void testUpdateOrder() throws Exception {
        mockMvc.perform(put("http://localhost:8888/api/order/{1}", 1L))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "admin1_firstname", authorities = {"ADMIN"})
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(delete("http://localhost:8888/api/order/{1}", 1L))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
