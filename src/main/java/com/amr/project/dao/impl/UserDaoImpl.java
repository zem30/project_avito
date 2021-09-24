package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import com.amr.project.util.QueryResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl extends ReadWriteDaoImp<User, Long> implements UserDao {
    private final EntityManager entityManager;

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

    @Override
    public List<User> findByRole(String role) throws NoResultException {
                /*
        select * from platform.user
 inner join platform.user_role on user.id = user_role.user_id
 inner join platform.role on user_role.role_id = role.id
 where role.role_name = 'User'
         */

        /*
        "SELECT u FROM User u INNER JOIN UserRole ur ON ur.user = u.username WHERE ur.role = 'ROLE_USER'"
         */
        TypedQuery<User> query = entityManager.createQuery("select * from User u " +
                "inner join User .on u.id = r.users  " +
//                "inner join Role on r.id = Role.id "+
                "where Role.name = :role", User.class);
        query.setParameter("role",role);
        return query.getResultList();
    }
}
