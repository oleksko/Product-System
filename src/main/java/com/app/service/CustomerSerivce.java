package com.app.service;

import com.app.model.dto.CustomerDto;

public interface CustomerSerivce {

    void add(CustomerDto customerDto);
    void remove(Long id);
}
