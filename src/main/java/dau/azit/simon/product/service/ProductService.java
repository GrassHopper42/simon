package dau.azit.simon.product.service;

import dau.azit.simon.product.domain.Location;
import dau.azit.simon.product.domain.Money;
import dau.azit.simon.product.domain.Product;
import dau.azit.simon.product.domain.ProductId;
import dau.azit.simon.product.dto.CreateProductDto;
import dau.azit.simon.product.dto.UpdateProductDto;
import dau.azit.simon.product.repository.ProductJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductJpaRepository productRepository;

    public ProductService(ProductJpaRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void registerProduct(CreateProductDto dto) {
        Product product = new Product(dto.code(), dto.name());
        productRepository.save(product);
    }

    public List<Product> searchProduct(String code, String name, String comment) {
        return productRepository.findAllByCodeContainingIgnoreCaseAndNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(code, name, comment);
    }

    @Transactional
    public void updateProduct(ProductId id, UpdateProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow();
        if (!dto.name().isBlank()) product.changeName(dto.name());
        if (!dto.location().isBlank()) product.stock(new Location(dto.location()));
        if (!dto.description().isBlank()) product.changeDescription(dto.description());
        if (dto.price() != null && dto.price() >= 0) {
            product.fixPrice(new Money(dto.price()));
        }
    }

    public void removeProduct(ProductId id) {
        Product product = productRepository.findById(id).orElseThrow();
        product.delete();
    }
}
