package com.leanpay.loancalculator.repository;

import com.leanpay.loancalculator.entities.CalculatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculatorRepository extends JpaRepository<CalculatorEntity, Integer> {}
