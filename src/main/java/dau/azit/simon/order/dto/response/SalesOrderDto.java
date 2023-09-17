package dau.azit.simon.order.dto.response;

import dau.azit.simon.order.domain.OrderStatus;
import dau.azit.simon.order.domain.SalesOrder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record SalesOrderDto(
		Long id,
		UUID uid,
		Date orderDate,
		String memo,
		long totalPrice,
		OrderStatus status,
		List<SalesOrderLineDto> orderLines
) {
	public static SalesOrderDto from(SalesOrder salesOrder) {
		return new SalesOrderDto(
				salesOrder.getId(),
				salesOrder.getUid(),
				salesOrder.getOrderDate(),
				salesOrder.getMemo(),
				salesOrder.getTotalPrice(),
				salesOrder.getStatus(),
				salesOrder.getOrderLines().stream().map(SalesOrderLineDto::from).toList()
		);
	}
}
