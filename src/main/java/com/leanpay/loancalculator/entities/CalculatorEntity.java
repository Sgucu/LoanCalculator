package com.leanpay.loancalculator.entities;

import jakarta.persistence.*;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "calculator")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class CalculatorEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  @CreatedDate Instant createdAt;
  Long loanAmount;
  Integer interestRate;
  Integer loanTerm;
  @NotNull String timeScope;
  Long monthlyPayment;
  Long totalInterest;
}
