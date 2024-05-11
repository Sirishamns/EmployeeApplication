package com.example.controllerclass;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemodel.Employee;
import com.example.employeemodel.TaxDeduction;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/employees")
@Validated
public class EmployeeController {
	@GetMapping("/tax-deduction")
	@PostMapping("/add")
	public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee) {
		// Code to save employee to database or perform other operations
		return new ResponseEntity<>("Employee added successfully", HttpStatus.OK);
	}

	public ResponseEntity<TaxDeduction> calculateTaxDeduction(@RequestParam String employeeCode,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date doj, @RequestParam double salaryPerMonth,
			@RequestParam int lossOfPayDays) {
//Calculate the number of months worked in the current financial year
		int monthsWorked = calculateMonthsWorked(doj);

// Calculate the total salary considering loss of pay
		double totalSalary = salaryPerMonth * monthsWorked - ((salaryPerMonth / 30) * lossOfPayDays);

// Calculate tax amount
		double taxAmount = calculateTaxAmount(totalSalary);

// Calculate cess amount
		double cessAmount = calculateCessAmount(totalSalary);

		TaxDeduction response = new TaxDeduction();
		response.setEmployeeId(employeeCode);
		response.setYearlySalary(totalSalary);
		response.setTaxAmount(taxAmount);
		response.setCessAmount(cessAmount);

// Return the tax deduction details
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private int calculateMonthsWorked(Date doj) {
		return 12 - doj.getMonth();
	}

	private double calculateTaxAmount(double yearlySalary) {
// Implement logic to calculate tax amount based on tax slabs
		if (yearlySalary <= 250000) {
			return 0;
		} else if (yearlySalary <= 500000) {
			return (yearlySalary - 250000) * 0.05;
		} else if (yearlySalary <= 1000000) {
			return 12500 + (yearlySalary - 500000) * 0.1;
		} else {
			return 12500 + 50000 + (yearlySalary - 1000000) * 0.2;
		}
	}

	private double calculateCessAmount(double yearlySalary) {
// Implement logic to calculate cess amount
// Collect additional 2% cess for the amount more than 2500000
// This is a simplified version for demonstration
// You can adjust the logic based on your actual rules
		if (yearlySalary > 2500000) {
			return (yearlySalary - 2500000) * 0.02;
		} else {
			return 0;
		}
	}
}
