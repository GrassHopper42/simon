package dau.azit.simon.product.domain;

import jakarta.persistence.*;

@Entity
public class Product {
    @EmbeddedId
    private ProductId id;
    @Column(name = "code", unique = true, length = 15)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "comment")
    private String comment;
    @Column(name = "location")
    private String location;

    public Product() {}

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Product(ProductId id, String code, String name, String comment, String location) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.comment = comment;
        this.location = location;
    }

    public void changeComment(String comment) {
        this.comment = comment;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}