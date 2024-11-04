package com.workintech.s18d4.service;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.CustomerRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer find(Long id) {
      return  customerRepository.findById(id).orElseThrow();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerResponse update(Long id, Customer customer) {
        Customer custom=customerRepository.findById(id).orElseThrow();
        custom.setFirstName(customer.getFirstName());
        custom.setLastName(customer.getLastName());
        custom.setEmail(customer.getEmail());
        custom.setSalary(customer.getSalary());
        custom.setAddress(customer.getAddress());
        customerRepository.save(custom);
        return new CustomerResponse(custom.getId(),custom.getEmail(),custom.getSalary());
    }

    @Override
    public Customer delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            customerRepository.delete(customer.get());
            return customer.get();
        }
        return null;
    }
}
