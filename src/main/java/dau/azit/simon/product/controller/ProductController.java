package dau.azit.simon.product.controller;

import dau.azit.simon.product.domain.ProductId;
import dau.azit.simon.product.dto.CreateProductDto;
import dau.azit.simon.product.dto.UpdateProductDto;
import dau.azit.simon.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void createProduct(@Valid @RequestBody CreateProductDto dto) {
        this.productService.registerProduct(dto);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable ProductId id, @RequestBody UpdateProductDto dto) {
        this.productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable ProductId id) {
        // TODO : Exception Handling
        this.productService.removeProduct(id);
    }
}
