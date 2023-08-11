package dau.azit.simon.order.dto;


import dau.azit.simon.order.domain.OrderLine;

import java.util.List;
import java.util.Optional;

public class OrderDto {

	public record CreateOrderDto(List<OrderLine> orderLines, Long customerId, Optional<String> memo) {
	}

	public record OrderLineDto(Long productId, int quantity) {
	}

}
