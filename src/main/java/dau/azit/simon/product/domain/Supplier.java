package dau.azit.simon.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity()
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "supplier")
    private List<ProductSupply> supplyItems;

    public Supplier(String name) {
        this.name = name;
    }
}