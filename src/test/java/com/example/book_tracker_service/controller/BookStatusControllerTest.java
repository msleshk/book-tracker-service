package com.example.book_tracker_service.controller;

import com.example.book_tracker_service.service.BookStatusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookStatusController.class)
class BookStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookStatusService bookStatusService;

    private static final String INVALID_JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyiZXhwIjoxNzM1NTU2MDg4fQ.gShNuD5Kdr_JfTSwtmkwQ";


    @Test
    void testAddBookStatus() throws Exception {
        mockMvc.perform(post("/book-status/1")
                        .header("Authorization", "Bearer " + INVALID_JWT_TOKEN))
                .andExpect(status().is(403));
    }

    @Test
    void testDeleteBookStatus() throws Exception {
        mockMvc.perform(delete("/book-status/1")
                        .header("Authorization", "Bearer " + INVALID_JWT_TOKEN))
                .andExpect(status().is(403));
    }

}



