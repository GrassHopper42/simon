package dau.azit.simon.order.dto;

import dau.azit.simon.customer.dto.CustomerDto;
import dau.azit.simon.order.domain.OrderStatus;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public record OrderDto() {

	public record CreateAdditionalOrderDto(List<OrderLineDto> orderLines, Long customerId, OrderStatus orderStatus,
	                                       Optional<String> memo) {
	}

	public record CreateFirstOrderDto(CustomerDto.CreateCustomerDto customer,
	                                  List<OrderLineDto> orderLines,
	                                  @NonNull()
	                                  OrderStatus orderStatus,
	                                  Optional<String> memo
	) {
	}

	public record OrderLineDto(Long productId, int quantity) {
	}

}
