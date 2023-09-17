package dau.azit.simon.order.dto.request;

import java.util.List;

public record CreateEstimationDto(
		Long customerId,

		List<CreateSalesOrderLineDto> salesOrderLines,

		String deliveryAddress,

		String memo
) {
}