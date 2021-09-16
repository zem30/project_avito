package com.amr.project.service.abstracts;

import com.amr.project.model.entity.User;

import java.util.Optional;

public interface UserService extends ReadWriteService<User, Long> {
    Optional<User> findByUsername(String username);
}
