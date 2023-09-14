package dau.azit.simon.customer.domain;

import dau.azit.simon.order.domain.SalesOrder;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Entity(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private UUID uid;

	@Column(nullable = false)
	String name;

	String companyName;

	String companyAddress;

	@Column(nullable = false)
	String contact;

	@OneToMany
	@Column(nullable = false)
	List<SalesOrder> orders;

	String companyRepresentative;

	public Customer() {
	}

	static public Customer createCustomer(String name, String contact, String companyName, String companyAddress, String companyRepresentative) {
		return new Customer(name, contact, companyName, companyAddress, companyRepresentative);
	}

	private Customer(String name, String contact, String companyName, String companyAddress, String companyRepresentative) {
		this.uid = UUID.randomUUID();
		this.name = name;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.contact = contact;
		this.companyRepresentative = companyRepresentative;
	}
}