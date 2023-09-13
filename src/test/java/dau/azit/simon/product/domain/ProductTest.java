package dau.azit.simon.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProductTest {

    @Nested
    @DisplayName("새 상품 생성")
    class CreateProduct {
        @Test
        @DisplayName("코드와 이름을 받으면 성공")
        void success() {
            // given
            String code = "00000001";
            String name = "test product";

            // when
            Product product = new Product(code, name);

            // then
            assertThat(product.getCode()).isEqualTo(code);
            assertThat(product.getName()).isEqualTo(name);
        }
    }

    @Nested
    @DisplayName("이름 변경 테스트")
    class ChangeNameTest {
        @Test
        @DisplayName("이름을 second로 변경")
        void changeName() {
            // given
            Product product = new Product("test", "first");

            // when
            product.changeName("second");

            // then
            assertThat(product.getName()).isEqualTo("second");
        }
    }
}