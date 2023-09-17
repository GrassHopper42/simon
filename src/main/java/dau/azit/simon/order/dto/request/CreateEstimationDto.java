package dau.azit.simon.order.dto.request;

import java.util.List;
import java.util.Optional;

public record CreateEstimationDto(
		Long customerId,

		List<CreateSalesOrderLineDto> salesOrderLines,

		String deliveryAddress,

		Optional<String> memo
) {
}