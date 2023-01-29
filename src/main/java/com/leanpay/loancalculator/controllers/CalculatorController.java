package com.leanpay.loancalculator.controllers;

import com.leanpay.loancalculator.models.LoanRequest;
import com.leanpay.loancalculator.models.LoanResponse;
import com.leanpay.loancalculator.services.CalculatorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CalculatorController {

  CalculatorService calculatorService;

  @PostMapping(ApiPaths.CALCULATE_LONA)
  public LoanResponse calculateLoan(@Valid @RequestBody LoanRequest request) {
    return calculatorService.calculateLoan(request);
  }
}
