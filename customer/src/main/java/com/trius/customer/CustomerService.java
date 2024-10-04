package com.trius.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).
                lastName(request.lastName()).
                email(request.email()).build();

        customerRepository.saveAndFlush(customer);
        //TODO: check isFraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.postForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                request,
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("is fraud");
        }
    }

    public Long deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.deleteById(id);
            return id;
        } else {
            return null;
        }
    }
}
