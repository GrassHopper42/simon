package dau.azit.simon.order.domain;

import dau.azit.simon.customer.domain.Customer;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sales_order")
@Getter
public class SalesOrder {
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

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "salesOrder", fetch = FetchType.EAGER)
	private List<SalesOrderLine> orderLines;

	@ManyToOne
	private Customer customer;

	public SalesOrder() {
	}


	public static SalesOrder createOrder(List<SalesOrderLine> orderLines, Customer customer, OrderStatus orderStatus, String memo) {
		return new SalesOrder(orderLines, customer, memo, orderStatus);
	}

	private void setSalesOrderLines(List<SalesOrderLine> salesOrderLines) {
		salesOrderLines.forEach(salesOrderLine -> salesOrderLine.setSalesOrder(this));
		this.orderLines = salesOrderLines;
	}

	private SalesOrder(List<SalesOrderLine> orderLines, Customer customer, String memo, OrderStatus status) {
		setSalesOrderLines(orderLines);
		this.uid = UUID.randomUUID();
		this.orderDate = new Date();
		this.memo = memo;
		this.customer = customer;
		this.totalPrice = orderLines.stream().mapToLong(SalesOrderLine::getTotalSalesPrice).sum();
		this.status = status;
	}

	public void cancel() {
		this.status = OrderStatus.ORDER_CANCEL;
	}

	public void restore() {
		this.status = OrderStatus.ORDER_COMPLETE;
	}

	public void changeStatus(OrderStatus orderStatus) {
		this.status = orderStatus;
	}

	public void convertToOrder() {
		if (!this.status.equals(OrderStatus.ESTIMATION)) {
			throw new IllegalStateException("주문 상태가 견적 상태가 아닙니다.");
		}
		this.status = OrderStatus.ORDER_COMPLETE;
	}
}
