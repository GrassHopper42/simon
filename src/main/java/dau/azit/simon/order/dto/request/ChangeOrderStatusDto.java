package dau.azit.simon.order.dto.request;

import dau.azit.simon.order.domain.OrderStatus;

public record ChangeOrderStatusDto(
		Long customerId,

		Long salesOrderId,

		OrderStatus orderStatus
) {
}