package com.leanpay.loancalculator.services.impl;

import com.leanpay.loancalculator.models.LoanRequest;
import com.leanpay.loancalculator.models.LoanResponse;
import com.leanpay.loancalculator.models.mappers.LoanMapper;
import com.leanpay.loancalculator.repository.CalculatorRepository;
import com.leanpay.loancalculator.services.CalculatorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CalculatorServiceImpl implements CalculatorService {

  CalculatorRepository calculatorRepository;
  LoanMapper mapper;

  static final String YEARS = "YEARS";

  @Override
  public LoanResponse calculateLoan(LoanRequest request) {
    var response = new LoanResponse();

    Integer loanAmount = request.getLoanAmount();
    Double rate = (request.getInterestRate() / 100) / 12;
    double monthlyPayment;

    int time = request.getLoanTerm();
    if (request.getTimeScope().equals(YEARS)) {
      time = time * 12;
    }
    monthlyPayment = (loanAmount * rate) / (1 - Math.pow(1 + rate, -time));
    Double totalInterestPayed = monthlyPayment * time - request.getLoanAmount();

    DecimalFormat towPlacesFormat = new DecimalFormat("##.00");
    response.setTotalInterest(Double.valueOf(towPlacesFormat.format(totalInterestPayed)));
    response.setMonthlyPayment(Double.valueOf(towPlacesFormat.format(monthlyPayment)));

    calculatorRepository.save(mapper.map(request, response));

    return response;
  }
}
