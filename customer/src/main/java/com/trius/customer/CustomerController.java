package com.trius.customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("api/v1/customers/")
public record CustomerController(CustomerService customerService) {


    @PostMapping("registerCustomer")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegistrationRequest customerRegRequest) {
        log.info("registerCustomer called" + customerRegRequest);
        customerService.registerCustomer(customerRegRequest);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @DeleteMapping("deleteCustomer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
        log.info("deleteCustomer called" + customerId);
        Long id = customerService.deleteCustomer(customerId);
        return id != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
