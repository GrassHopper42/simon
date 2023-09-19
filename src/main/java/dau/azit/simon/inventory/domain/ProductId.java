package dau.azit.simon.inventory.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Embeddable
public class ProductId implements Serializable {
    private Long id;
}
