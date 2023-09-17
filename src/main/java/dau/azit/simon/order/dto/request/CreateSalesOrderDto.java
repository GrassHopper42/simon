package dau.azit.simon.order.dto.request;

import dau.azit.simon.order.domain.OrderStatus;
import lombok.NonNull;

import java.util.List;

public record CreateSalesOrderDto(
		Long customerId,

		List<CreateSalesOrderLineDto> salesOrderLines,

		String deliveryAddress,

		String memo,

		@NonNull
		OrderStatus orderStatus
) {
}