package com.kaplan.ecommerce.customer.repository;

import com.kaplan.ecommerce.customer.entity.Customer;
import com.kaplan.ecommerce.customer.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {

    public Customer findByNumberID(String numberId);
    public List<Customer> finfByLastName(String lastName);
    public List<Customer> findByFirstName(String firstName);
    public List<Customer> findByLocation(Location location);
}
