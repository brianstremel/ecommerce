package com.kaplan.ecommerce.customer.service;

import com.kaplan.ecommerce.customer.entity.Customer;
import com.kaplan.ecommerce.customer.entity.Location;
import com.kaplan.ecommerce.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findCustomerByNumberID(String numberID) {
        return customerRepository.findByNumberID(numberID);
    }

    @Override
    public List<Customer> findCustomersByRegion(Location location) {
        return customerRepository.findByLocation(location);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());
        if(customer != null) {
            return customerDB;
        }

        customer.setState("CREATED");
        customerDB = customerRepository.save(customer);
        return customerDB;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if(customerDB == null) {
            return null;
        }

        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setEmail(customer.getEmail());
        customer.setPhotoUrl(customer.getPhotoUrl());
        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if(customerDB == null) {
            return null;
        }

        customer.setState("DELETED");
        return customerRepository.save(customer);
    }
}
