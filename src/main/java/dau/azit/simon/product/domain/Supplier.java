package dau.azit.simon.product.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity()
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "contact")
    private String contact;

    @OneToMany
    private List<ProductSupply> supplyItems;

    public Supplier() {}

    public Supplier(String name) {
        this.name = name;
    }

    public Supplier(Long id, String name, String contact, List<ProductSupply> supplyItems) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.supplyItems = supplyItems;
    }
}