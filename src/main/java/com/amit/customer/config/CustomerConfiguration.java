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
