package com.trius.customer;

public record CustomerService() {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().
                firstName(request.firstName()).
                lastName(request.lastName()).
                email(request.email()).build();
    }
}
