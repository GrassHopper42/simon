package dau.azit.simon.product.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
public class Product {
    @EmbeddedId
    private ProductId id;
    @Column(name = "code", unique = true, length = 15)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "location")
    private String location;
    @Column(nullable = true)
    private LocalDateTime deletedAt;

    public Product() {}

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Product(ProductId id, String code, String name, String comment, String location) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = comment;
        this.location = location;
    }

    public void changeDescription(String comment) {
        this.description = comment;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}