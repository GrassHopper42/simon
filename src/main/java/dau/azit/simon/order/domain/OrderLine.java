package dau.azit.simon.order.domain;

import dau.azit.simon.product.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Table(name = "orderLines")
public class OrderLine {
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

	public OrderLine(Product product, int quantity) {
		this.uid = UUID.randomUUID();
		this.product = product;
		this.quantity = quantity;

		// TODO : 가격 기능 추가 필요
	}

	public OrderLine() {
	}
}
