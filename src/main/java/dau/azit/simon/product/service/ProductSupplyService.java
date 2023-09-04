package dau.azit.simon.product.service;

import dau.azit.simon.product.domain.Money;
import dau.azit.simon.product.domain.ProductSupply;
import dau.azit.simon.product.domain.ProductSupplyKey;
import dau.azit.simon.product.domain.Supplier;
import dau.azit.simon.product.dto.CreateSupplierDto;
import dau.azit.simon.product.dto.RegistSupplyDto;
import dau.azit.simon.product.repository.ProductSupplyJpaRepository;
import dau.azit.simon.product.repository.SupplierJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductSupplyService {
    private final SupplierJpaRepository supplierRepository;
    private final ProductSupplyJpaRepository productSupplyRepository;

    public ProductSupplyService(SupplierJpaRepository repository, ProductSupplyJpaRepository productSupplyRepository) {
        this.supplierRepository = repository;
        this.productSupplyRepository = productSupplyRepository;
    }

    public void registSupplier(CreateSupplierDto dto) {
        Supplier supplier = new Supplier(dto.name());
        supplierRepository.save(supplier);
    }

    public void supplyProduct(RegistSupplyDto dto) {
        ProductSupply newSupply = new ProductSupply(
                new ProductSupplyKey(dto.productId(), dto.supplierId()),
                new Money(dto.price()),
                dto.code(),
                dto.memo()
        );

        productSupplyRepository.save(newSupply);
    }
}
