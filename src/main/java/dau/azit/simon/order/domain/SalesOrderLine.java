package dau.azit.simon.order.domain;

import dau.azit.simon.product.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Table(name = "sales_order_line")
public class SalesOrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private UUID uid;

	@OneToOne
	private Product product;

	@Column(nullable = false)
	int quantity;

	@Column(nullable = false)
	int salesPrice;

	@Column(nullable = false)
	String publicProductName;

	public SalesOrderLine(Product product, int quantity, String publicProductName) {
		this.uid = UUID.randomUUID();
		this.product = product;
		this.quantity = quantity;
		this.publicProductName = publicProductName;

		// TODO : 가격 기능 추가 필요
	}

	public SalesOrderLine() {
	}
}
