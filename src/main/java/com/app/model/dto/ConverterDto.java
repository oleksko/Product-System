package com.app.model.dto;

import com.app.model.Country;
import com.app.model.Customer;
import com.app.model.Producer;
import com.app.model.Trade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

public class ConverterDto {
    public TradeDto fromTradeToTradeDto(Trade trade) {
        return trade == null ? null : new TradeDto(
                trade.getId(),
                trade.getName()
        );
    }

    public Trade fromTradeDtoToTrade(TradeDto tradeDto) {
        return tradeDto == null ? null : new Trade(
                tradeDto.getId(),
                tradeDto.getName(),
                new HashSet<>()
        );
    }

    public CountryDto fromCountryToCountryDto(Country country) {
        return country == null ? null : new CountryDto(
                country.getId(),
                country.getName()
        );
    }

    public Country fromCountryDtoToCountry(CountryDto countryDto) {
        return countryDto == null ? null : new Country(
                countryDto.getId(),
                countryDto.getName(),
                new HashSet<>(),
                new HashSet<>(),
                new HashSet<>()
        );
    }

    public ProducerDto fromProducerToProducerDto(Producer producer) {
        return producer == null ? null : new ProducerDto(
                producer.getId(),
                producer.getName(),
                fromCountryToCountryDto(producer.getCountry()),
                fromTradeToTradeDto(producer.getTrade())
        );
    }

    public Producer fromProducerDtotoProducer(ProducerDto producerDto)
    {
        return producerDto == null ? null : new Producer(
                producerDto.getId(),
                producerDto.getName(),
                fromCountryDtoToCountry(producerDto.getCountryDto()),
                fromTradeDtoToTrade(producerDto.getTradeDto()),
                new HashSet<>()
        );
    }

    public Customer fromCustomerDtotoCustomer(CustomerDto customerDto)
    {
        return customerDto == null ? null : new Customer(
                customerDto.getId(),
                customerDto.getAge(),
                customerDto.getName(),
                customerDto.getSurname(),
                fromCountryDtoToCountry(customerDto.getCountryDto()),
                new HashSet<>()

        );
    }

    public CustomerDto fromCustomertoCustomerDto(Customer customer)
    {
        return customer == null ? null: new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getAge(),
                customer.getSurname(),
                fromCountryToCountryDto(customer.getCountry())
        );
    }
}
