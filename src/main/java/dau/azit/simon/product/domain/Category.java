package dau.azit.simon.product.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
@Table(
        name = "category",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "category_name_unique",
                        columnNames = {"parent_category_id", "name"}
                )
        }
)
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory", orphanRemoval = true)
    private Set<Category> childCategories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Product> products;

    public void addChild(String name) {
        childCategories.add(new Category(name));
    }

    public boolean hasProduct() {
        return !this.products.isEmpty();
    }
}
