package com.app.dao;

import com.app.model.Product;
import com.app.model.exceptions.MyException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractGenericDao<T> implements GenericDao<T> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void add(T t) {
        if (entityManager != null && t != null) {
            entityManager.merge(t);
        }
    }

    @Override
    public void update(T t) {

        if (entityManager != null && t != null) {
            entityManager.merge(t);
        }

    }

    @Override
    public void delete(Long id) {
        try {
            T t = entityManager.find(entityClass, id);
            entityManager.remove(t);
        } catch (Exception e) {
            throw new MyException(entityClass + ", delete: " + e.getMessage(), LocalDateTime.now());
        }
    }

    @Override
    public Optional<T> findOneById(Long id) {
        Optional<T> optional = Optional.empty();

        if (id != null) {
            optional = Optional.ofNullable(entityManager.find(entityClass, id));
        }

        return optional;
    }

    @Override
    public List<T> findAll() {
        System.out.println(entityClass.toString());
        Query query = entityManager.createQuery("select t from " + entityClass.getCanonicalName() + " t");
        List<T> elements = query.getResultList();
        System.out.println(elements.toString());
        return elements;

    }

    @Override
    public T getById(Long id) {
        T t = entityManager.find(entityClass, id);
        return t;
    }
}
