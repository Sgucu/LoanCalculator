package com.leanpay.loancalculator.services.impl;

import com.leanpay.loancalculator.entities.CalculatorEntity;
import com.leanpay.loancalculator.models.LoanRequest;
import com.leanpay.loancalculator.repository.CalculatorRepository;
import com.leanpay.loancalculator.services.CalculatorService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class CalculatorServiceImplTest {

  @Autowired
  CalculatorService service;

  @MockBean
  CalculatorRepository repository;


  @Test
  void calculateLoan() {
    LoanRequest request = new LoanRequest(1000, 5D, 10, "MONTHS");

    when(repository.save(any(CalculatorEntity.class))).thenReturn(new CalculatorEntity());

    var savedService = service.calculateLoan(request);
    assertNotNull(savedService, "The saved result should not be null");
  }
}
