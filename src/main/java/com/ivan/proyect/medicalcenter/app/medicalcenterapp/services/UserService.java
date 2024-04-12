package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services;

import java.util.List;
import java.util.Optional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.UserDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.security.User;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.UserRequest;

public interface UserService {
    List<UserDto> findAll();
    UserDto save(User user);
    Optional<User> findById(Long id) ;
    Optional<User> delete(Long id);
    Optional<UserDto> update(Long id, UserRequest user);
}
