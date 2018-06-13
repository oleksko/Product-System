package com.app.service;

import com.app.dao.CountryDao;
import com.app.dao.CustomerDao;
import com.app.model.Country;
import com.app.model.Customer;
import com.app.model.dto.ConverterDto;
import com.app.model.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerSerivce {
    private CustomerDao customerDao;
    private CountryDao countryDao;
    private ConverterDto converterDto;

    public CustomerServiceImpl(CustomerDao customerDao, CountryDao countryDao, ConverterDto converterDto) {
        this.customerDao = customerDao;
        this.countryDao = countryDao;
        this.converterDto = converterDto;
    }

    @Override
    public void add(CustomerDto customerDto) {
        if(customerDto != null)
        {
            System.out.println("customer dto");
            System.out.println(customerDto.toString());

            Country country = converterDto.fromCountryDtoToCountry(customerDto.getCountryDto());
            System.out.println(country.toString());
            System.out.println(customerDto.getAge());

            System.out.println(country.toString());
            ModelMapper mapper = new ModelMapper();
            PropertyMap<CustomerDto, Customer> propertyMap = new PropertyMap<CustomerDto, Customer>() {
                @Override
                protected void configure() {
                    map().setName(source.getName());
                    map().setSurname(source.getSurname());
                    map().setAge(source.getAge());
                    map().setCountry(country);
                }
            };
            mapper.addMappings(propertyMap);
            Customer customer = mapper.map(customerDto, Customer.class);
            customerDao.add(customer);

        }
    }

    @Override
    public void remove(Long id) {
        Customer customer = customerDao.findOneById(id).get();
        customerDao.delete(customer.getId());
    }

    @Override
    public Optional<CustomerDto> getById(Long id) {
        Optional<CustomerDto> optionalCustomerDto = Optional.empty();
        if(id != null)
        {
            Customer customer = customerDao.getById(id);
            optionalCustomerDto = Optional.of(converterDto.fromCustomertoCustomerDto(customer));
        }
        return optionalCustomerDto;
    }
}
