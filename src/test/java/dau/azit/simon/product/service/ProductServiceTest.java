package dau.azit.simon.product.service;

import dau.azit.simon.product.domain.Product;
import dau.azit.simon.product.domain.ProductId;
import dau.azit.simon.product.dto.CreateProductDto;
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


    @Nested
    @DisplayName("상품 생성 테스트")
    class RegisterProductTest {
        @Test
        @DisplayName("성공")
        void success() {
            // given
            String code = "test0001";
            String name = "test product";
            CreateProductDto dto = new CreateProductDto(code, name);
            Product product = new Product(new ProductId(1L), code, name, null, null, null, null);

            given(productRepository
                    .save(any(Product.class)))
                    .willReturn(product);

            // when
            service.registerProduct(dto);

            // then
            then(productRepository).should().save(any(Product.class));
        }
    }
}