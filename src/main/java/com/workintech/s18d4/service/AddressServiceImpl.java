package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address find(long id) {
        return addressRepository.findById(id).orElseThrow();
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address, long addressId) {
        Address existingAddress = addressRepository.findById(addressId).orElseThrow();
        existingAddress.setStreet(address.getStreet());
        existingAddress.setNo(address.getNo());
        existingAddress.setCity(address.getCity());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setDescription(address.getDescription());
        return addressRepository.save(existingAddress);
    }

    @Override
    public Address delete(long id) {
        Address address = addressRepository.findById(id).orElseThrow();
        addressRepository.delete(address);
        return address;
    }
}
