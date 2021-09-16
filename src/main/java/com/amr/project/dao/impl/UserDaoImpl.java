package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserDaoImpl extends ReadWriteDaoImp<User, Long> implements UserDao {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Optional<User> findByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findAny();
    }
}
