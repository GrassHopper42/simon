package dau.azit.simon.product.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductId implements Serializable {
    @GeneratedValue
    private Long id;

    public ProductId() {}

    public ProductId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductId productId)) return false;

        return Objects.equals(id, productId.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
