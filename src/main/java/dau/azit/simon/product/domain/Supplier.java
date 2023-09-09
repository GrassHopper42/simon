package dau.azit.simon.product.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity()
public class Supplier {
    @EmbeddedId
    private SupplierId id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "supplier")
    private List<ProductSupply> supplyItems;

    public Supplier() {}

    public Supplier(String name) {
        this.name = name;
    }

    public Supplier(SupplierId id, String name, String contact, List<ProductSupply> supplyItems) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }
}