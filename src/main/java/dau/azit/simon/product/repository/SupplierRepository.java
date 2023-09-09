package dau.azit.simon.product.repository;

import dau.azit.simon.product.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
