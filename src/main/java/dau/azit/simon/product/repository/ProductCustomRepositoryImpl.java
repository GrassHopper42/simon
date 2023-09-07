package dau.azit.simon.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dau.azit.simon.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

import static dau.azit.simon.product.domain.QProduct.product;

@Repository
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory queryFactory;

    public ProductCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Product> searchProduct(String code, String name, String description) {
        return queryFactory.selectFrom(product)
                .where(product.code.containsIgnoreCase(code)
                        .and(product.name.containsIgnoreCase(name))
                        .and(product.description.containsIgnoreCase(description))
                )
                .fetch();
    }
}
