package com.example.book_tracker_service.globalExceptionHandler;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHandleBookStatusNotFoundException() throws Exception {
        mockMvc.perform(get("/non-existent-endpoint")
                .header("X-Throw-Error", "BookStatusNotFoundException"));
    }

    @Test
    void testHandleGenericException() throws Exception {
        mockMvc.perform(get("/non-existent-endpoint")
                .header("X-Throw-Error", "GenericException"));
    }
}

