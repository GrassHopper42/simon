package dau.azit.simon.order.dto.request;

import java.util.List;

public record RestoreSalesOrdersDto(
		Long customerId,

		List<Long> salesOrderIds
) {
}