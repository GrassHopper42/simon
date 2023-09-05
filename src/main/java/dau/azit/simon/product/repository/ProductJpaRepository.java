package dau.azit.simon.product.repository;

import dau.azit.simon.product.domain.Product;
import dau.azit.simon.product.domain.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, ProductId> {
    Optional<Product> findByCode(String code);
    List<Product> findAllByCodeContainingIgnoreCaseAndNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(String code, String name, String description);
}
