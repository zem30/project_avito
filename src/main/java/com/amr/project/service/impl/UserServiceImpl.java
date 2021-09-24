package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User,Long> implements UserService {

    private final UserDao userDao;

    protected UserServiceImpl(ReadWriteDao<User, Long> dao, UserDao userDao1) {
        super(dao);
        this.userDao = userDao1;
    }

    @Override
    public User findByUsername(String username) throws NoResultException {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) throws NoResultException {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) throws NoResultException {
        return userDao.findByPhone(phone);
    }

    @Override
    public List<User> findByRole(String role) throws NoResultException {
        return userDao.findByRole(role);
    }
}
