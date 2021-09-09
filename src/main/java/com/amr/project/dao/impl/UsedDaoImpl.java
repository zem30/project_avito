package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UsedDaoImpl extends ReadWriteDaoImp<User, Long> implements UserDao {

    @Override
    public User getByEmail(String email) {
        Query query = entityManager.createQuery("select u from User u where u.email = :email");
        query.setParameter("email", email);
        return query.getSingleResult() == null ? null : (User) query.getSingleResult() ;
    }
}
