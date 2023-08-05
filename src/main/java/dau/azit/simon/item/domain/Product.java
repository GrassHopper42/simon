package dau.azit.simon.item.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "comment")
    private String comment;
    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "product")
    private Set<Code> codes;

    public Product() {}

    public Product(Long id, String name, String comment, String location, Set<Code> codes) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.location = location;
        this.codes = codes;
    }
}