package dau.azit.simon.order.domain;

import dau.azit.simon.customer.domain.Customer;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
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

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<OrderLine> orderLines;

	// TODO : 실제 Customer 클래스로 수정 필요
	@ManyToOne()
	private Customer customer;

	public Order() {
	}


	static public Order createOrder(List<OrderLine> orderLines, Customer customer, OrderStatus orderStatus, String memo) {
		return new Order(orderLines, customer, memo, orderStatus);
	}


	private Order(List<OrderLine> orderLines, Customer customer, String memo, OrderStatus status) {
		this.uid = UUID.randomUUID();
		this.orderDate = new Date();
		this.memo = memo;
		this.orderLines = orderLines;
		this.customer = customer;
		this.totalPrice = orderLines.stream().mapToLong(OrderLine::getSalesPrice).sum();
		this.status = status;
	}

}
