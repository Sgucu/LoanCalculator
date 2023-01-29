package com.leanpay.loancalculator.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LoanRequest {
  Integer loanAmount;
  Double interestRate;
  Integer loanTerm;
  String timeScope;
}
