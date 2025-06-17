package com.bazar.bazarbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazar.bazarbooks.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
