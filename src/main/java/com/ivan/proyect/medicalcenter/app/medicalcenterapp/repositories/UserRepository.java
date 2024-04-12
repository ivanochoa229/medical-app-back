package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.security.User;

public interface UserRepository extends CrudRepository<User, Long>{
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
