package com.amr.project.dao.abstracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public abstract class ReadWriteDaoImp<T, K> implements ReadWriteDao<T, K> {

    Class<T> typeClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<T> getAll() {
        return (List<T>) entityManager.createQuery("from " + typeClass.getSimpleName()).getResultList();
    }

    @Override
    public void persist(T obj) {
        entityManager.persist(obj);
    }

    @Override
    public void update(T obj) {
        entityManager.merge(obj);
    }

    @Override
    public void delete(T obj) {
        entityManager.remove(entityManager.contains(obj) ? obj : entityManager.merge(obj));
    }

    @Override
    public void deleteByKeyCascadeEnable(K key) {
        entityManager.remove(entityManager.find(typeClass, key));
    }

    @Override
    public void deleteByKeyCascadeIgnore(K key) {
        Query query = entityManager.createQuery(
                "DELETE FROM " + typeClass.getName() + " u WHERE u.id = :id");
        query.setParameter("id", key);
        query.executeUpdate();
    }

    @Override
    public boolean existsById(K key) {
        int caunt = entityManager.createQuery("select u from " + typeClass.getSimpleName() + " u where  u.id= '" + key + "' ").getResultList().size();
        return caunt > 0 ? true : false;
    }

    @Override
    public T getByKey(K key) {
        return entityManager.find(typeClass, key);
    }
}
