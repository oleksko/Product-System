package com.app.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private Integer age;
    private String name;
    private String surname;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id")
    private Country country;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
    private Set<CustomerOrder> customer_orders = new LinkedHashSet<>();


}
