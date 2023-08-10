package dau.azit.simon.order.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private UUID uid;

	@Column(nullable = false)
	private Date orderDate;

	private String memo;

	@Column(nullable = false)
	private long totalPrice;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;


	public Order() {
	}

	private Order(String memo, long totalPrice, OrderStatus status) {
		this.uid = UUID.randomUUID();
		this.orderDate = new Date();
		this.memo = memo;
		this.totalPrice = totalPrice;
		this.status = status;
	}

}

enum OrderStatus {
	ESTIMATE, COMPLETE
}