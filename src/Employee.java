package com.example.employeemodel;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class Employee {
	@NotBlank(message = "Employee ID is mandatory") 
    private String employeeId;
	@NotBlank(message = "First Name is mandatory")
    private String firstName;
	@NotBlank(message = "Last Name is mandatory")
    private String lastName;
	@Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;
	@NotEmpty(message = "Phone Numbers should not be empty")
    private List<@Pattern(regexp = "\\d{10}", message = "Phone Number should be 10 digits") String> phoneNumbers;

    @NotNull(message = "Date of Joining is mandatory")
    private Date doj;

    @Positive(message = "Salary should be a positive value")
    private double salary;
//getters and setters method
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
    
}
