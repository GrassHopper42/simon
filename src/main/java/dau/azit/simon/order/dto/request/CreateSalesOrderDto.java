package dau.azit.simon.order.dto.request;

import dau.azit.simon.order.domain.OrderStatus;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public record CreateSalesOrderDto(
		Long customerId,

		List<CreateSalesOrderLineDto> salesOrderLines,

		String deliveryAddress,

		Optional<String> memo,

		@NonNull()
		OrderStatus orderStatus
) {
}