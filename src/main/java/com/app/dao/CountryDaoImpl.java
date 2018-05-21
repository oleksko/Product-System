package com.app.dao;

import com.app.model.Country;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public class CountryDaoImpl extends AbstractGenericDao<Country> implements CountryDao {

/*    @Override
    public Optional<Country> findOneById(Long id) {
        Optional<Country> optionalCountry = Optional.empty();
        if(getEntityManager() != null && id != null)
        {
            optionalCountry = Optional.of(getEntityManager().find(Country.class, id));
        }
        return optionalCountry;
    }*/
}
