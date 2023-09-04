package dau.azit.simon.product.repository;

import dau.azit.simon.product.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierJpaRepository extends JpaRepository<Supplier, Long> {
}
