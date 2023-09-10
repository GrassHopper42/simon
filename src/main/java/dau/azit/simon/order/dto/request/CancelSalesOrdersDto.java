package dau.azit.simon.order.dto.request;

import java.util.List;

public record CancelSalesOrdersDto(
		Long customerId,

		List<Long> salesOrderIds

) {
}