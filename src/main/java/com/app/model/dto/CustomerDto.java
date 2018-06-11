package com.app.model.dto;


import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String name;
    private Integer age;
    private String surname;
    private CountryDto countryDto;
}
