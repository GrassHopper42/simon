package dau.azit.simon.order.domain.mock;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity()
public class Product {
	@Id
	private Long id;

	public int getPrice() {
		return 1000;
	}
}