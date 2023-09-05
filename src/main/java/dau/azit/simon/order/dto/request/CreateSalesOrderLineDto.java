package dau.azit.simon.order.dto.request;

public record CreateSalesOrderLineDto(
		String productCode,

		String publicProductName,

		int quantity,

		int salesPrice
) {
}
