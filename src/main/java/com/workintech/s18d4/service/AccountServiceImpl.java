package com.workintech.s18d4.service;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account find(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @Override
    public Account save(Long customer_id, Account account) {
        Customer customer = customerRepository.findById(customer_id).orElseThrow();
        customer.addAccount(account);
        account.setCustomer(customer);
        accountRepository.save(account);
        return account;
    }

    @Override
    public CustomerResponse update(Long customer_id, Account account) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.addAccount(account);
        account.setCustomer(customer);
        customerRepository.save(customer);
        return new CustomerResponse(customer_id, customer.getEmail(), customer.getSalary());
    }


    @Override
    public Account delete(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            accountRepository.delete(account.get());
            return account.get();
        }
        return null;
    }

    @Override
    public Account save(Account account) {
      return  accountRepository.save(account);
    }
}
