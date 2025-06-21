package com.bazar.bazarbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bazar.bazarbooks.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}