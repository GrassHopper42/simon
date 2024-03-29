package dau.azit.simon.product.service;

import dau.azit.simon.product.domain.Category;
import dau.azit.simon.product.domain.Product;
import dau.azit.simon.product.dto.CreateProductDto;
import dau.azit.simon.product.repository.CategoryRepository;
import dau.azit.simon.product.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;


    @Nested
    @DisplayName("상품 생성 테스트")
    class RegisterProductTest {
        @Test
        @DisplayName("성공")
        void success() {
            // given
            String code = "test0001";
            String name = "test product";
            CreateProductDto dto = new CreateProductDto(1L, code, name);

            given(productRepository
                    .save(any(Product.class)))
                    .willReturn(new Product());
            given(categoryRepository
                    .getReferenceById(1L))
                    .willReturn(new Category("test"));

            // when
            service.registerProduct(dto);

            // then
            then(productRepository).should().save(any(Product.class));
        }
    }
}