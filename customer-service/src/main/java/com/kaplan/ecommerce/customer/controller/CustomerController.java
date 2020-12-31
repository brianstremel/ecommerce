package com.kaplan.ecommerce.customer.controller;


import brave.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaplan.ecommerce.customer.entity.Customer;
import com.kaplan.ecommerce.customer.entity.Location;
import com.kaplan.ecommerce.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers(@RequestParam(name = "regionId", required = false) Long regionId) {
        List<Customer> customers = new ArrayList<>;
        if(null == regionId) {
            customers = customerService.findCustomerAll();
            if(customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            Location location = new Location();
            location.setId(regionId);
            customers = customerService.findCustomersByRegion(location);
            if(null == customers) {
                log.error("Customers with region id {} not found.", regionId);
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
        log.info("Fetching customer with id {}", id);
        Customer customer = customerService.getCustomer(id);
        if(null == customer) {
            log.error("Customer with id {} not found." id);
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        log.info("Updating Customer with id {}.", id);
        Customer currentCustomer = customerService.getCustomer(id);
        if (null == currentCustomer) {
            log.error("Unable to update. Customer with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        customer.setId(id);
        currentCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(currentCustomer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {
        log.info("Fetching & Deleting customer with id {}.", id);
        Customer customer = customerService.getCustomer(id);
        if(null == customer) {
            log.error("Unable to delete. Customer with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        customer = customerService.deleteCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    private String formatMesssage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
