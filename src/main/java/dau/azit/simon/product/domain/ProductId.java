package dau.azit.simon.product.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;

import java.io.Serializable;

@Embeddable
public class ProductId implements Serializable {
    @GeneratedValue
    private Long id;
}
