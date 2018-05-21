package com.app.service;

import com.app.dao.CountryDao;
import com.app.dao.ShopDao;
import com.app.model.Country;
import com.app.model.Shop;
import com.app.model.dto.ShopDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    private CountryDao countryDao;
    private ShopDao shopDao;


    public ShopServiceImpl(CountryDao countryDao, ShopDao shopDao) {
        this.countryDao = countryDao;
        this.shopDao = shopDao;
    }

    @Override
    public void add(ShopDto shopDto) {
        if(shopDto != null)
        {
           Country country = countryDao.findOneById(shopDto.getCountryId()).get();
            ModelMapper mapper = new ModelMapper();
            PropertyMap<ShopDto, Shop> propertyMap = new PropertyMap<ShopDto, Shop>() {
                @Override
                protected void configure() {
                    map().setName(source.getName());
                    map().setCountry(country);
                }
            };
            mapper.addMappings(propertyMap);
            Shop shop = mapper.map(shopDto, Shop.class);
            shopDao.add(shop);
        }

    }
}
