package dau.azit.simon.order.dto;

import dau.azit.simon.customer.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public record OrderDto() {

	public record CreateAdditionalOrderDto(List<OrderLineDto> orderLines, Long customerId, Optional<String> memo) {
	}

	public record CreateFirstOrderDto(CustomerDto.CreateCustomerDto customer,
	                                  List<OrderLineDto> orderLines,
	                                  Optional<String> memo
	) {
	}

	public record OrderLineDto(Long productId, int quantity) {
	}

}
