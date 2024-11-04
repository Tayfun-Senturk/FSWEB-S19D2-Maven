package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAdresses(){
        return addressService.findAll();
    }
    @GetMapping("/{id}")
    public Address getAdress(@PathVariable Long id){
        return addressService.find(id);
    }
    @PostMapping
    public Address saveAdress(@RequestBody Address address){
        return addressService.save(address);
    }
    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id ,@RequestBody Address address){
        return addressService.update(address,id);
    }
    @DeleteMapping("/{id}")
    public Address deleteAdress(@PathVariable Long id){
        return addressService.delete(id);
    }

}
