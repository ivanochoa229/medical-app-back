package com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.util.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
