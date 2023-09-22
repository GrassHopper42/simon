package dau.azit.simon.inventory.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Inventory {
    @EmbeddedId
    private ProductId productId;

    @ElementCollection
    @CollectionTable(name = "inventory_stock", joinColumns = @JoinColumn(name = "product_id"))
    @OrderColumn(name = "priority")
    private List<Stock> stocks;

    public Quantity getAvailableQuantity() {
        return stocks.stream()
                .map(Stock::getQuantity)
                .reduce(Quantity::add)
                .orElse(Quantity.zero());
    }

    public Quantity getQuantity(Location location) {
        return findStock(location)
                .map(Stock::getQuantity)
                .orElse(Quantity.zero());
    }

    public void putaway(Location location, Quantity quantity) {
        Stock stock = findStock(location)
                .orElseThrow(() -> new IllegalArgumentException("재고가 할당되지 않은 위치입니다."));
        stock.putaway(quantity);
    }

    public void pick(Location location, Quantity quantity) {
        Stock stock = findStock(location)
                .orElseThrow(() -> new IllegalArgumentException("재고가 할당되지 않은 위치입니다."));
        stock.pick(quantity);
    }

    public void allocate(Location location) {
        if (findStock(location).isPresent()) {
            return;
        }
        Stock stock = new Stock(location, Quantity.zero());
        stocks.add(stock);
    }

    public void deallocate(Location location) {
        findStock(location)
                .filter(Stock::checkRemain)
                .ifPresentOrElse(s -> stocks.remove(s), () -> {
                    throw new IllegalArgumentException("재고가 남아있어 할당 해제할 수 없습니다.");
                });
    }

    private Optional<Stock> findStock(Location location) {
        return stocks.stream()
                .filter(s -> s.getLocation().equals(location))
                .findFirst();
    }
}
