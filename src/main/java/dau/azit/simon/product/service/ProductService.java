package dau.azit.simon.product.service;

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

    public Product getProductByCode(String code) {
        return productRepository.findByCode(code).orElseThrow();
    }

    public List<Product> findByName(String name) {
        return productRepository.findAllByNameContainsIgnoreCase(name);
    }

    public void searchProduct(String code, String name, String comment) {
        // TODO : 상품 검색 기능
    }

    @Transactional
    public void updateProduct(ProductId id, UpdateProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow();
        if (!dto.location().isBlank()) product.setLocation(dto.location());
        if (!dto.comment().isBlank()) product.changeComment(dto.comment());
    }

    public void removeProduct(ProductId id) {
        // TODO : Soft delete
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }
}
