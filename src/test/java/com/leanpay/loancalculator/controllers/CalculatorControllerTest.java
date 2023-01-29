package com.leanpay.loancalculator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanpay.loancalculator.models.LoanRequest;
import com.leanpay.loancalculator.models.LoanResponse;
import com.leanpay.loancalculator.services.CalculatorService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
class CalculatorControllerTest {

    @MockBean
    CalculatorService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @Test
    @DisplayName("Test of Calculate loan")
    void calculateLoan() throws Exception {
        LoanRequest request = new LoanRequest(1000, 5D, 10, "MONTHS");
        LoanResponse response = new LoanResponse( 102.31D,  23.06D);

        doReturn(response).when(service).calculateLoan(request);

        mockMvc.perform(post("/calculate-loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.monthlyPayment", is(response.getMonthlyPayment())))
                .andExpect(jsonPath("$.totalInterest", is(response.getTotalInterest())));
    }
}