package com.thoughtworks.capacity.gtb.mvc.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_success_when_register() throws Exception {
        String jsonUser = "{\"username\": \"Tom\",\"password\": \"12345\",\"email\": \"tom@qq.com\"}";
        mockMvc.perform(post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonUser))
                    .andExpect(status().isCreated());
    }
}