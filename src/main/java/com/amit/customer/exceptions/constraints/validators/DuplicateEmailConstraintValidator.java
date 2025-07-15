package com.amit.customer.exceptions.constraints.validators;


import org.springframework.beans.factory.annotation.Autowired;

import com.amit.customer.exceptions.constraints.DuplicateEmailConstraint;
import com.amit.customer.service.CustomerService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DuplicateEmailConstraintValidator implements ConstraintValidator<DuplicateEmailConstraint,String>
{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public void initialize(DuplicateEmailConstraint contactNumber) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext cxt) {
		return customerService.getCustomerByEmail(email) == null;
	}
}
