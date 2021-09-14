package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import com.amr.project.util.QueryResultWrapper;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class UserDaoImpl extends ReadWriteDaoImp<User, Long> implements UserDao {

    @Override
    public User getByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);
        return QueryResultWrapper.wrapGetSingleResult(query);
    }
}
