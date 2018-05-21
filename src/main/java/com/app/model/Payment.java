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
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private EPayment payment;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "payment")
    private Set<CustomerOrder> customer_orders = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment1 = (Payment) o;
        return id == payment1.id &&
                payment == payment1.payment &&
                Objects.equals(customer_orders, payment1.customer_orders);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", payment=" + payment +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, payment);


    }
}
