package dau.azit.simon.order.controller;


import dau.azit.simon.order.domain.SalesOrder;
import dau.azit.simon.order.dto.request.CancelSalesOrdersDto;
import dau.azit.simon.order.dto.request.ChangeOrderStatusDto;
import dau.azit.simon.order.dto.request.CreateSalesOrderDto;
import dau.azit.simon.order.dto.request.RestoreSalesOrdersDto;
import dau.azit.simon.order.service.SalesOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales-orders")
public class SalesOrderController {
	private final SalesOrderService salesOrderService;

	@PostMapping()
	public ResponseEntity<SalesOrder> createOrder(@Valid @RequestBody() CreateSalesOrderDto dto) {
		salesOrderService.createSalesOrder(dto);
		return ResponseEntity.ok(null);
	}

	@PatchMapping("/cancel")
	public ResponseEntity<Void> cancelOrder(@Valid @RequestBody() CancelSalesOrdersDto dto) {
		salesOrderService.cancelSalesOrders(dto.customerId(), dto.salesOrderIds());
		return ResponseEntity.ok(null);
	}

	@PatchMapping("/restore")
	public ResponseEntity<Void> restoreOrders(@Valid @RequestBody() RestoreSalesOrdersDto dto) {
		salesOrderService.restoreSalesOrders(dto.customerId(), dto.salesOrderIds());
		return ResponseEntity.ok(null);
	}

	@PatchMapping("/status")
	public ResponseEntity<Void> changeOrderStatus(@Valid @RequestBody() ChangeOrderStatusDto dto) {
		salesOrderService.changeOrderStatus(dto.customerId(), dto.salesOrderId(), dto.orderStatus());
		return ResponseEntity.ok(null);
	}

}
