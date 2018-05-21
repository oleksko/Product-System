package com.app.service;

import com.app.model.Product;
import com.app.model.dto.ProductDto;

import java.util.Optional;

public interface ProductService {

    void add(ProductDto productDto);
    Optional<ProductDto> getById(Long id);
}
