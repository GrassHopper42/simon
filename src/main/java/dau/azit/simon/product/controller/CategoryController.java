package dau.azit.simon.product.controller;

import dau.azit.simon.product.dto.CategoryListDto;
import dau.azit.simon.product.dto.CreateCategoryDto;
import dau.azit.simon.product.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryListDto>> listCategory() {
        List<CategoryListDto> categories = this.categoryService.findAllCategory()
                .stream()
                .map(category -> new CategoryListDto(category.getId(), category.getName()))
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public void createCategory(@RequestBody CreateCategoryDto dto) {
        this.categoryService.createCategory(dto.parentId(), dto.name());
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.removeCategory(categoryId);
    }
}
