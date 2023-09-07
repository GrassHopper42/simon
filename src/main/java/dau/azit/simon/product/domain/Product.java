package dau.azit.simon.product.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Product {
    @EmbeddedId
    private ProductId id;
    @Column(name = "code", unique = true, length = 15)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "standard")
    private String standard;
    @Column(name = "description")
    private String description;
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;
    @Embedded
    private Location location;
    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;
    @OneToMany(mappedBy = "product")
    private List<ProductSupply> supplies;

    public Product() {}

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Product(ProductId id, String code, String name, String standard, String description, Money price, Location location) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.standard = standard;
        this.description = description;
        this.price = price;
        this.location = location;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String comment) {
        this.description = comment;
    }

    public void fixPrice(Money price) {
        this.price = price;
    }

    public void stock(Location location) {
        this.location = location;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}