package com.app.service;

import com.app.model.dto.CustomerDto;

import java.util.Optional;

public interface CustomerSerivce {

    void add(CustomerDto customerDto);
    void remove(Long id);
    Optional<CustomerDto> getById(Long id);
}
