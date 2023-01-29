package com.leanpay.loancalculator.services;

import com.leanpay.loancalculator.models.LoanRequest;
import com.leanpay.loancalculator.models.LoanResponse;

public interface CalculatorService {
  LoanResponse calculateLoan(LoanRequest request);
}
