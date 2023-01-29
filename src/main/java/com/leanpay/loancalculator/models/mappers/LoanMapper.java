package com.leanpay.loancalculator.models.mappers;

import com.leanpay.loancalculator.entities.CalculatorEntity;
import com.leanpay.loancalculator.models.LoanRequest;
import com.leanpay.loancalculator.models.LoanResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {
  CalculatorEntity map(LoanRequest request, LoanResponse response);
}
