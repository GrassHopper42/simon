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

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(nullable = false)
	int quantity;

	@Column(nullable = false)
	int salesPrice;

	@Column(nullable = false)
	String publicProductName;

	@ManyToOne
	@JoinColumn(name = "sales_order_id")
	private SalesOrder salesOrder;

	public SalesOrderLine(Product product, int quantity, String publicProductName, int salesPrice) {
		this.uid = UUID.randomUUID();
		this.product = product;
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
