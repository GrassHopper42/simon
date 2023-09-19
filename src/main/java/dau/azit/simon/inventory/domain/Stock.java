package dau.azit.simon.inventory.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Stock {
    @ManyToOne
    private Location location;
    @AttributeOverride(name = "value", column = @Column(name = "quantity"))
    private Quantity quantity;

    public static Stock allocate(Location location) {
        return new Stock(location, Quantity.zero());
    }

    public void putaway(Quantity quantity) {
        this.quantity = this.quantity.add(quantity);
    }

    public void pick(Quantity quantity) {
        if (quantity.isGreaterThan(this.quantity)) {
            throw new IllegalArgumentException("재고량이 부족합니다.");
        }
        this.quantity = this.quantity.subtract(quantity);
    }

    public boolean checkRemain() {
        return quantity.isGreaterThan(Quantity.zero());
    }
}