package dau.azit.simon.product.service;

import dau.azit.simon.product.domain.*;
import dau.azit.simon.product.dto.CreateProductDto;
import dau.azit.simon.product.dto.UpdateProductDto;
import dau.azit.simon.product.repository.CategoryRepository;
import dau.azit.simon.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void registerProduct(CreateProductDto dto) {
        Category category = this.categoryRepository.getReferenceById(dto.categoryId());
        Product product = new Product(dto.code(), category, dto.name());
        productRepository.save(product);
    }

    public List<Product> searchProduct(String code, String name, String comment) {
        return productRepository.searchProduct(code, name, comment);
    }

    @Transactional
    public void updateProduct(ProductId id, UpdateProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow();
        if (!dto.name().isBlank()) product.changeName(dto.name());
        if (!dto.location().isBlank()) product.updateLocation(new Location(dto.location()));
        if (!dto.description().isBlank()) product.changeDescription(dto.description());
        if (dto.price() != null && dto.price() >= 0) {
            product.fixPrice(new Money(dto.price()));
        }
    }

    public void changeCategory(ProductId id, Long categoryId) {
        Category category = this.categoryRepository.getReferenceById(categoryId);
        Product product = this.productRepository.findById(id).orElseThrow();
        product.changeCategory(category);
    }

    public void removeProduct(ProductId id) {
        Product product = productRepository.findById(id).orElseThrow();
        product.delete();
    }
}
