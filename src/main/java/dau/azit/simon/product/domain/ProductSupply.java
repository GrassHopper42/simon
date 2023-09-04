package dau.azit.simon.product.domain;

import jakarta.persistence.*;

@Entity
public class ProductSupply {
    @EmbeddedId
    private ProductSupplyKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @Column
    private String supplierCode;
    @Column
    private String memo;

    public ProductSupply() {
    }

    public ProductSupply(ProductSupplyKey id, Money price, String supplierCode, String memo) {
        this.id = id;
        this.price = price;
        this.supplierCode = supplierCode;
        this.memo = memo;
    }
}
