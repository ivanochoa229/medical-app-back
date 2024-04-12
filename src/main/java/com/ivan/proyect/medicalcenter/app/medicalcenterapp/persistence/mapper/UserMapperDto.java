package com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.mapper;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.UserDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.security.User;

public class UserMapperDto {
    private User user;

    private UserMapperDto() {
    }

    public static UserMapperDto builder() {
        return new UserMapperDto();
    }

    public UserMapperDto setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDto build() {
        if (user == null) {
            throw new RuntimeException("You must pass the entity user");
        } else {
            boolean isAdmin = user.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"));
            boolean isDoctor = user.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_DOC"));
            return new UserDto(user.getId(), user.getUsername(), user.getEmail(),isAdmin, isDoctor);
        }
    }
}
