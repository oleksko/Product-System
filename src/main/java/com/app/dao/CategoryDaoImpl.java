package com.app.dao;

import com.app.model.Category;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoryDaoImpl extends AbstractGenericDao<Category> implements CategoryDao {
    @Override
    public List<Category> findAll() {
        EntityManager entityManager = getEntityManager();
        //Query query = entityManager.createQuery("select c from Category c join fetch c.products p");
        Query query = entityManager.createQuery("select c from Category c");
        List<Category> categories = query.getResultList();

        for (Category c : categories)
        {
            /*if (c.getProducts() != null)
            {
                c.getProducts().size();
            }*/
            Hibernate.initialize(c.getProducts());
        }


        return categories;
    }

    @Override
    public Category getById(Long id) {
        Category category = null;
        if( id != null && getEntityManager() != null)
        {
            category = getEntityManager().find(Category.class, id);
        }
        return category;
    }
}
