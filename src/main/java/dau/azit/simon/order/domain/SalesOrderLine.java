package dau.azit.simon.order.domain;


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

	@Column(nullable = false)
	private Long productId;

	@Column(nullable = false)
	int quantity;

	@Column(nullable = false)
	int salesPrice;

	@Column(nullable = false)
	String publicProductName;

	@ManyToOne
	@JoinColumn(name = "sales_order_id")
	private SalesOrder salesOrder;

	public SalesOrderLine(Long productId, int quantity, String publicProductName, int salesPrice) {
		this.uid = UUID.randomUUID();
		this.productId = productId;
		this.quantity = quantity;
		this.publicProductName = publicProductName;
		this.salesPrice = salesPrice;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public SalesOrderLine() {
	}

	public int getTotalSalesPrice() {
		return salesPrice * quantity;
	}
}
