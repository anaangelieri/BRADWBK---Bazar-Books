package com.bazar.bazarbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bazar.bazarbooks.model.Address;
import com.bazar.bazarbooks.service.AddressService;

@RestController
@RequestMapping("addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        Address address = addressService.getAddressById(id);
        if (address != null) {
            return ResponseEntity.ok(address);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address created = addressService.createAddress(address);
        return ResponseEntity.ok(created);
    }

    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address address) {
        boolean updated = addressService.updateAddress(id, address);
        if (updated) {
            return ResponseEntity.ok(address);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable int id) {
        boolean deleted = addressService.deleteAddress(id);
        if (deleted) {
            return ResponseEntity.ok("Endereço apagado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }
}