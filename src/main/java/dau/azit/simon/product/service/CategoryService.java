package dau.azit.simon.product.service;

import dau.azit.simon.product.domain.Category;
import dau.azit.simon.product.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    public List<Category> findAllCategory() {
        return this.categoryRepository.findAll();
    }

    @Transactional
    public void createCategory(Long parentCategoryId, String name) {
        if (Objects.isNull(parentCategoryId)) {
            Category category = new Category(name);
            this.categoryRepository.save(category);
        } else {
            Category parent = this.categoryRepository.findById(parentCategoryId).orElseThrow();
            parent.addChild(name);
        }
    }

    public void removeCategory(Long categoryId) {
        Category targetCategory = this.categoryRepository.getReferenceById(categoryId);
        if (targetCategory.hasProduct()) throw new IllegalArgumentException();
        this.categoryRepository.delete(targetCategory);
    }
}
