package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDaoImpl extends ReadWriteDaoImp<User, Long> implements UserDao {
    @Override
    public User findByUsername(String username) throws NoResultException {
        return (User) this.entityManager.createQuery("from User where username = :username").setParameter("username", username).getSingleResult();
    }

    @Override
    public User findByEmail(String email) throws NoResultException{
        return (User) this.entityManager.createQuery("from User where email = :email").setParameter("email", email).getSingleResult();
    }

    @Override
    public User findByPhone(String phone) throws NoResultException{
        return (User) this.entityManager.createQuery("from User where phone = :phone").setParameter("phone", phone).getSingleResult();
    }
}
