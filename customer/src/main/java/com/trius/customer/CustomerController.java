package com.trius.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {


    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegRequest) {
        log.info("registerCustomer called" + customerRegRequest);
        customerService.registerCustomer(customerRegRequest);
    }
}
