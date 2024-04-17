package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.UserDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.security.User;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.mapper.UserMapperDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.UserRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.util.Role;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.DoctorRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.RoleRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.UserRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    private RoleRepository roleRepository;
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map( u -> UserMapperDto.builder().setUser(u).build()).collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public UserDto save(User user) {
        List<Role> roles = new ArrayList<>();
        if(user.isDoctor()){
            Optional<Role> optionaRoleUser = roleRepository.findByName("ROLE_DOC");
            optionaRoleUser.ifPresent(roles::add);
            if(user.isAdmin()){
                Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
                optionalRoleAdmin.ifPresent(roles::add);
            }
        }else{
            Optional<Role> optionaRoleUser = roleRepository.findByName("ROLE_PAT");
            optionaRoleUser.ifPresent(roles::add);
        }
        user.setRoles(roles);
        user.setPassword(user.getPassword());
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapperDto.builder().setUser(userRepository.save(user)).build();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<User> findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser;
        }
        return optionalUser;
    }

    @SuppressWarnings("null")
    @Override
    public Optional<User> delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            // if(user.getPatient() != null || user.getDoc() != null){
            //     return optionalUser;
            // }else{
            //     userRepository.delete(user);
            // }
        }
        return optionalUser;
    }

    @SuppressWarnings("null")
    @Override
    public Optional<UserDto> update(Long id, UserRequest user) {
        Optional<User> optionalUser = userRepository.findById(id);
        List<Role> roles = new ArrayList<>();
        if(optionalUser.isPresent()){
            User updatedUser = optionalUser.get();
            // if(updatedUser.getDoc() != null){
            //     Optional<Role> optionaRoleUser = roleRepository.findByName("ROLE_DOC");
            //     optionaRoleUser.ifPresent(roles::add);
            //     if(user.isAdmin()){
            //         Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            //         optionalRoleAdmin.ifPresent(roles::add);
            //     }
            // }else{
            //     Optional<Role> optionaRoleUser = roleRepository.findByName("ROLE_PAT");
            //     optionaRoleUser.ifPresent(roles::add);
            // }
            updatedUser.setUsername(user.getUsername());
            updatedUser.setRoles(roles);
            updatedUser.setEmail(user.getEmail());
            //updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            return Optional.of(UserMapperDto.builder().setUser(userRepository.save(updatedUser)).build());
        }
        return Optional.empty();
    }
}
