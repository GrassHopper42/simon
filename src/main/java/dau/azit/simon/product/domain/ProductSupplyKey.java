package dau.azit.simon.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductSupplyKey implements Serializable {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "supplier_id")
    private Long supplierId;

    public ProductSupplyKey() {}

    public ProductSupplyKey(Long productId, Long supplierId) {
        this.productId = productId;
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSupplyKey that)) return false;

        if (!Objects.equals(productId, that.productId)) return false;
        return Objects.equals(supplierId, that.supplierId);
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (supplierId != null ? supplierId.hashCode() : 0);
        return result;
    }
}
