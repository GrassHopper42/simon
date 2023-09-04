package dau.azit.simon.product.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Money {
    private int amount;

    protected Money() {}

    public Money(int amount) {
        if (amount < 0) throw new IllegalArgumentException();
        this.amount = amount;
    }

    public Money add(Money money) {
        return new Money(money.amount + this.amount);
    }
}
