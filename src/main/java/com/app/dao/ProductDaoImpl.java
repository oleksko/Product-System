package com.app.dao;

import com.app.model.Product;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductDaoImpl extends AbstractGenericDao<Product> implements ProductDao {

    @Override
    public Optional<Product> findOneById(Long id) {
        Optional<Product> optionalProduct = Optional.empty();
        if(getEntityManager() != null && id != null)
        {
            javax.persistence.Query query = getEntityManager().createQuery("select p from Product p where p.id = :id");
            query.setParameter("id", id);
            Product product = (Product) query.getSingleResult();
            if(product.getProducer() != null && product.getCategory() != null && product.getGuarantee_components() != null)
            {
                Hibernate.initialize(product.getCategory());
                Hibernate.initialize(product.getProducer());
                Hibernate.initialize(product.getGuarantee_components());
            }
            optionalProduct = Optional.ofNullable(product);

        }   return optionalProduct;
    }
}
