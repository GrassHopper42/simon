package dau.azit.simon.item.domain;

import jakarta.persistence.*;

@Entity
public class Code {
    @EmbeddedId
    private CodeId id;

    @ManyToOne
    @MapsId("typeId")
    @JoinColumn(name = "type_id")
    private CodeType type;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "value")
    private String value;

    public Code() {}

    public Code(CodeId id, CodeType type, Product product, String value) {
        this.id = id;
        this.type = type;
        this.product = product;
        this.value = value;
    }
}