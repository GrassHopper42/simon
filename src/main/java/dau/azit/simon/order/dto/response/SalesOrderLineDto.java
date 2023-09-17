package dau.azit.simon.order.dto.response;

import dau.azit.simon.order.domain.SalesOrderLine;

import java.util.UUID;

public record SalesOrderLineDto(
		Long id,
		UUID uid,
		Long productId,
		int quantity,
		int salesPrice,
		String publicProductName
) {
	public static SalesOrderLineDto from(SalesOrderLine salesOrderLine) {
		return new SalesOrderLineDto(
				salesOrderLine.getId(),
				salesOrderLine.getUid(),
				salesOrderLine.getProductId(),
				salesOrderLine.getQuantity(),
				salesOrderLine.getSalesPrice(),
				salesOrderLine.getPublicProductName()
		);
	}
}
