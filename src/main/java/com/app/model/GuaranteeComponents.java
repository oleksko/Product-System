package com.app.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuaranteeComponents {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "table_gurantee", joinColumns = @JoinColumn(name = "guarantee_id"))
    @Column(name = "guarantee_component_id")
    private List<EGuarantee> guarantee_component;

}
