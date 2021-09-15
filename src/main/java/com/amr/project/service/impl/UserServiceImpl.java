package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import com.amr.project.service.email.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User, Long> implements UserService {

    private final UserDao userDAO;
    private PasswordEncoder passwordEncoder;
    private EmailVerificationService verificationService;

    @Autowired
    public UserServiceImpl(UserDao userDAO, EmailVerificationService verificationService) {
        super(userDAO);
        this.userDAO = userDAO;
        this.verificationService = verificationService;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) throws NoResultException {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) throws NoResultException {
        return userDAO.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByPhone(String phone) throws NoResultException {
        return userDAO.findByPhone(phone);
    }

    @Override
    @Transactional
    public void persist(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.persist(user);
        verificationService.sendVerificationEmail(user);

    }

}
