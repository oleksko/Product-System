package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Shop> shops = new LinkedHashSet<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Producer> producers = new LinkedHashSet<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Customer> customers = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id &&
                Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
