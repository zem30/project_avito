package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import com.amr.project.util.QueryResultWrapper;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class UserDaoImpl extends ReadWriteDaoImp<User, Long> implements UserDao {
    @Override
    public User findByUsername(String username) throws NoResultException {
        TypedQuery<User> query = entityManager.createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        return QueryResultWrapper.wrapGetSingleResult(query);
    }

    @Override
    public User findByEmail(String email) throws NoResultException {
        TypedQuery<User> query = entityManager.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        return QueryResultWrapper.wrapGetSingleResult(query);
    }

    @Override
    public User findByPhone(String phone) throws NoResultException {
        TypedQuery<User> query = entityManager.createQuery("from User where phone = :phone", User.class);
        query.setParameter("phone", phone);
        return QueryResultWrapper.wrapGetSingleResult(query);
    }
}
