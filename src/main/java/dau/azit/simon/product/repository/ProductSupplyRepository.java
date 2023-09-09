package dau.azit.simon.product.repository;

import dau.azit.simon.product.domain.ProductSupply;
import dau.azit.simon.product.domain.ProductSupplyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSupplyRepository extends JpaRepository<ProductSupply, ProductSupplyKey> {
}
