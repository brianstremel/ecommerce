package com.kaplan.ecommerce.customer.service;

import com.kaplan.ecommerce.customer.entity.Customer;
import com.kaplan.ecommerce.customer.entity.Location;

import java.util.List;

public interface CustomerService {

    public List<Customer> findCustomerAll();
    public Customer getCustomer(Long id);
    public Customer findCustomerByNumberID(String numberID);
    public List<Customer> findCustomersByRegion(Location location);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);

}
