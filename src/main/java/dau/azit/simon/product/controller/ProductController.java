package dau.azit.simon.product.controller;

import dau.azit.simon.product.domain.Product;
import dau.azit.simon.product.domain.ProductId;
import dau.azit.simon.product.dto.*;
import dau.azit.simon.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(SearchProductDto dto){
        String code = dto.code() == null ? "" : dto.code();
        String name = dto.name() == null ? "" : dto.name();
        String comment = dto.comment() == null ? "" : dto.comment();
        return ResponseEntity.ok(this.productService.searchProduct(code, name, comment));
    }

    @PostMapping
    public void createProduct(@RequestBody CreateProductDto dto) {
        this.productService.registerProduct(dto);
    }


    @PutMapping("/{id}")
    public void updateProduct(@PathVariable ProductId id, @RequestBody UpdateProductDto dto) {
        this.productService.updateProduct(id, dto);
    }

    @PatchMapping("/{id}/category")
    public void changeProductCategory(@PathVariable ProductId id, @RequestBody ChangeCategoryDto dto) {
        this.productService.changeCategory(id, dto.categoryId());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable ProductId id) {
        this.productService.removeProduct(id);
    }
}
