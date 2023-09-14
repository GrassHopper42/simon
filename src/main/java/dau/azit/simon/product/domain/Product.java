package dau.azit.simon.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
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
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @OneToMany(mappedBy = "product")
    private List<ProductSupply> supplies;

    public Product(String code, Category category, String name) {
        this.code = code;
        this.category = category;
        this.name = name;
    }

    public void changeCategory(Category newCategory) {
        this.category = newCategory;
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

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}