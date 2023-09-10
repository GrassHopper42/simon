package dau.azit.simon.estimation.domain;

import dau.azit.simon.customer.domain.Customer;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "estimation")
@Getter
public class Estimation {
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

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "estimation")
	private List<EstimationLine> estimationLines;

	@ManyToOne()
	private Customer customer;

	public Estimation() {
	}


	public static Estimation createEstimation(List<EstimationLine> estimationLines, Customer customer, String memo) {
		return new Estimation(estimationLines, customer, memo);
	}

	private void setEstimationLines(List<EstimationLine> estimationLines) {
		estimationLines.forEach(estimationLine -> estimationLine.setEstimation(this));
		this.estimationLines = estimationLines;
	}

	private Estimation(List<EstimationLine> estimationLines, Customer customer, String memo) {
		setEstimationLines(estimationLines);
		this.uid = UUID.randomUUID();
		this.orderDate = new Date();
		this.memo = memo;
		this.customer = customer;
		this.totalPrice = estimationLines.stream().mapToLong(EstimationLine::getTotalSalesPrice).sum();
	}

}