package com.amit.customer.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Configuration
public class CustomerConfiguration {

	@Bean
	public Validator validator(final AutowireCapableBeanFactory autowireCapableBeanFactory) {

		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
				.constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
				.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		return validator;
	}

}


// AI-Generated Fix: Add Custom Validation Error Handler
// Generated on: 2025-07-18T09:57:49.572Z

try {
    // Validation logic
    validator.validate(engineDto);
} catch (ConstraintViolationException e) {
    Map<String, String> errors = new HashMap<>();
    e.getConstraintViolations().forEach(violation -> {
        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
    });
    throw new ValidationException("Validation failed", errors);
}