package com.app.dao;

import com.app.model.Shop;

import java.util.Optional;

public interface ShopDao extends GenericDao<Shop> {

    public Optional<Shop> findOneById(Long id);
}
