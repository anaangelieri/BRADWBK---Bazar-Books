package com.bazar.bazarbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazar.bazarbooks.model.Address;
import com.bazar.bazarbooks.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(int id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public boolean updateAddress(int id, Address updatedAddress) {
        if (addressRepository.existsById(id)) {
            updatedAddress.setIdAddress(id);
            addressRepository.save(updatedAddress);
            return true;
        }
        return false;
    }

    public boolean deleteAddress(int id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}