package dau.azit.simon.product.service;

import dau.azit.simon.product.domain.*;
import dau.azit.simon.product.dto.CreateSupplierDto;
import dau.azit.simon.product.dto.RegistSupplyDto;
import dau.azit.simon.product.repository.ProductSupplyRepository;
import dau.azit.simon.product.repository.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductSupplyService {
    private final SupplierRepository supplierRepository;
    private final ProductSupplyRepository productSupplyRepository;

    public ProductSupplyService(SupplierRepository repository, ProductSupplyRepository productSupplyRepository) {
        this.supplierRepository = repository;
        this.productSupplyRepository = productSupplyRepository;
    }

    public void registSupplier(CreateSupplierDto dto) {
        Supplier supplier = new Supplier(dto.name());
        supplierRepository.save(supplier);
    }

    public void supplyProduct(RegistSupplyDto dto) {
        ProductId productId = new ProductId(dto.productId());
        SupplierId supplierId = new SupplierId(dto.supplierId());
        Money price = new Money(dto.price());

        ProductSupply newSupply = new ProductSupply(
                new ProductSupplyKey(productId, supplierId),
                price,
                dto.code(),
                dto.memo()
        );

        productSupplyRepository.save(newSupply);
    }
}
