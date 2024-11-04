package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workintech.s18d4.repository.AccountRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private Double salary;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;


    public void addAccount(Account account){
        accounts.add(account);
    }
}
