package com.leanpay.loancalculator.reporsitory;

import com.leanpay.loancalculator.entities.CalculatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<CalculatorEntity, Integer> {
}
