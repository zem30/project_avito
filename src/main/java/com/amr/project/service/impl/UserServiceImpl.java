package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.dao.impl.UserDaoImpl;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User, Long> implements UserService {

    UserDao dao;
    protected UserServiceImpl(UserDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return dao.findByUsername(username);
    }
}
