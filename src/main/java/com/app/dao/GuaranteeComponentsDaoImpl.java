package com.app.dao;

import com.app.model.Category;
import com.app.model.GuaranteeComponents;
import com.app.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class GuaranteeComponentsDaoImpl extends AbstractGenericDao<GuaranteeComponents> implements GuaranteeComponentsDao {
/*    @Override
    public void add(GuaranteeComponents guaranteeComponents) {
        if(getEntityManager() != null && guaranteeComponents != null) {
            getEntityManager().persist(guaranteeComponents);
        }

    }*/
}
