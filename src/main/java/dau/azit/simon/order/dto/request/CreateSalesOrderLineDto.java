package dau.azit.simon.order.dto.request;

public record CreateSalesOrderLineDto(
		Long productId,

		String publicProductName,

		int quantity,

		int salesPrice
) {
}
