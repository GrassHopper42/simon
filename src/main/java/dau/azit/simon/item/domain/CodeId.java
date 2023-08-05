package dau.azit.simon.item.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CodeId implements Serializable {
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "product_id")
    private Long productId;

    public CodeId() {}

    public CodeId(Long typeId, Long productId) {
        this.typeId = typeId;
        this.productId = productId;
    }
}
