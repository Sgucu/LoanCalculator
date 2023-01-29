package com.leanpay.loancalculator.reporsitory;

import com.leanpay.loancalculator.entities.CalculatorEntity;
import com.leanpay.loancalculator.models.LoanResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CalculatorRepositoryTest {

	@LocalServerPort
	int port;

	String baseUrl = "http://localhost";

	static RestTemplate restTemplate;

	@Autowired
	TestH2Repository repository;

	@BeforeAll
	public static void init(){
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setBaseUrl(){
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/calculate-loan");
	}


	@Test
	public void testInsertionYears(){
		CalculatorEntity calculator = new CalculatorEntity(1, Instant.parse("2023-01-28T08:30:00Z"),
				20000L, 8, 5, "YEARS" , 405L, 4331L);

		var response = restTemplate.postForObject(baseUrl, calculator, LoanResponse.class);

		assertEquals(405.53D, response.getMonthlyPayment());
		assertEquals(4331.67D, response.getTotalInterest());
		assertEquals(2, repository.findAll().size());
	}

	@Test
	public void testInsertionMonths(){
		CalculatorEntity calculator = new CalculatorEntity(1, Instant.parse("2023-01-28T08:30:00Z"),
				150000L, 4, 30, "MONTHS" , 5262L, 7874L);

		var response = restTemplate.postForObject(baseUrl, calculator, LoanResponse.class);

		assertEquals( 5262.49D, response.getMonthlyPayment());
		assertEquals(7874.63D, response.getTotalInterest());
		assertEquals(1, repository.findAll().size());
	}

}
