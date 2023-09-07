package dau.azit.simon.product.repository;

import dau.azit.simon.product.domain.Product;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> searchProduct(String code, String name, String description);
}
