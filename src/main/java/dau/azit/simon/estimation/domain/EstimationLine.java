package dau.azit.simon.estimation.domain;

import dau.azit.simon.product.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Table(name = "estimation_line")
public class EstimationLine {
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
	@JoinColumn(name = "estimation_id")
	private Estimation estimation;

	public EstimationLine(Product product, int quantity, String publicProductName, int salesPrice) {
		this.uid = UUID.randomUUID();
		this.product = product;
		this.quantity = quantity;
		this.publicProductName = publicProductName;
		this.salesPrice = salesPrice;
	}

	public void setEstimation(Estimation estimation) {
		this.estimation = estimation;
	}

	public int getTotalSalesPrice() {
		return salesPrice * quantity;
	}

	public EstimationLine() {
	}
}
