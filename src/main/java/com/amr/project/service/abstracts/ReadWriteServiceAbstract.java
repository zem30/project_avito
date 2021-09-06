package com.amr.project.service.abstracts;

import com.amr.project.dao.abstracts.ReadWriteDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class ReadWriteServiceAbstract<T, K> implements ReadWriteService<T, K> {

    @Autowired
    protected ReadWriteDao<T, K> dao;

    @Override
    public void persist(T obj) {
        dao.persist(obj);
    }

    @Override
    public void update(T obj) {
        dao.update(obj);
    }

    @Override
    public void delete(T obj) {
        dao.delete(obj);
    }

    @Override
    public void deleteByKeyCascadeEnable(K key) {
        dao.deleteByKeyCascadeEnable(key);
    }

    @Override
    public void deleteByKeyCascadeIgnore(K key) {
        dao.deleteByKeyCascadeIgnore(key);
    }

    @Override
    public boolean existsById(K id) {
        return dao.existsById(id);
    }

    @Override
    public T getByKey(K key) {
        return dao.getByKey(key);
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }
}