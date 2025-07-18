package com.amit.customer.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.customer.domain.Customer;
import com.amit.customer.mapstruct.mappers.CustomerMapper;
import com.amit.customer.repository.CustomerRepository;
import com.amit.customer.service.CustomerService;
import com.amit.customer.web.model.CustomerDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerMapper customerMapper;

	@Operation(summary = "Get all customers")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the customer", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }) })
	@GetMapping("")
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		try {

			List<CustomerDto> customersResp = customerService.getAllCustomers();

			if (customersResp.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(customersResp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Get a customer by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the customer", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Customer not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(
			@Parameter(description = "id of customer to be searched") @PathVariable("id") long id) {
		CustomerDto customerDto = customerService.getCustomerById(id);

		if (customerDto != null) {
			return new ResponseEntity<>(customerDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Create a new customer")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Customer created successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input provided") })
	@PostMapping("")
	public ResponseEntity<CustomerDto> createCustomer(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Customer to create", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class), examples = @ExampleObject(value = "{ \"customerName\": \"Amit Bhagra\", \"email\": \"amit.bhagra@gmail.com\", \"mobile\": \"9879879879\" }"))) @Valid @RequestBody CustomerDto customerDto) {
		try {
			CustomerDto customerDTO = customerService.createCustomer(customerDto);
			return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerDto customer) {

		CustomerDto customerDto = customerService.updateCustomer(id, customer);

		if (customerDto != null) {

			return new ResponseEntity<>(customerDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
		try {
			customerService.deleteCustomer(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}


// AI-Generated Fix: Add Defensive Programming
// Generated on: 2025-07-18T09:01:11.685Z

// Use Optional to handle null safely
Optional<CustomerDto> customerOpt = Optional.ofNullable(customerService.getCustomerById(id));
if (customerOpt.isPresent()) {
    return new ResponseEntity<>(customerOpt.get(), HttpStatus.OK);
} else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}