package dau.azit.simon.inventory.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Quantity {
    private int value;

    public static Quantity zero() {
        return new Quantity(0);
    }

    public boolean isGreaterThan(Quantity other) {
        return value > other.value;
    }

    public boolean isGreaterThanOrEqual(Quantity other) {
        return value >= other.value;
    }

    public boolean isLessThan(Quantity other) {
        return value < other.value;
    }

    public Quantity add(Quantity other) {
        return new Quantity(value + other.value);
    }

    public Quantity subtract(Quantity other) {
        return new Quantity(value - other.value);
    }
}
