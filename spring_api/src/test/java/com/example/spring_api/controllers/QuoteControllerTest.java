package com.example.spring_api.controllers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;

import com.example.spring_api.services.QuoteService;

class QuoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuoteService quoteService;

    @InjectMocks
    private QuoteController quoteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(quoteController).build();
    }

    @Test
    void testGetQuote() throws Exception {
        String mockQuote = "This is a test quote.";
        when(quoteService.getQuote()).thenReturn(CompletableFuture.completedFuture(mockQuote));

        mockMvc.perform(asyncDispatch(mockMvc.perform(get("/quote")).andReturn()))
                .andExpect(status().isOk())
                .andExpect(content().string(mockQuote));
    }
}
