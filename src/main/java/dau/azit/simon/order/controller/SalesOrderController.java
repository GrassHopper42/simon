package dau.azit.simon.order.controller;

import dau.azit.simon.order.domain.SalesOrder;
import dau.azit.simon.order.dto.request.*;
import dau.azit.simon.order.dto.response.SalesOrderDto;
import dau.azit.simon.order.service.SalesOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class SalesOrderController {
	private final SalesOrderService salesOrderService;

	@PostMapping
	public ResponseEntity<SalesOrderDto> createOrder(@Valid @RequestBody CreateSalesOrderDto dto) {
		SalesOrder salesOrder = salesOrderService.createSalesOrder(dto);
		return ResponseEntity.ok(SalesOrderDto.from(salesOrder));
	}

	@PatchMapping("/cancel")
	public ResponseEntity<List<SalesOrderDto>> cancelOrder(@Valid @RequestBody CancelSalesOrdersDto dto) {
		List<SalesOrder> salesOrders = salesOrderService.cancelSalesOrders(dto.customerId(), dto.salesOrderIds());
		return ResponseEntity.ok(salesOrders.stream().map(SalesOrderDto::from).collect(Collectors.toList()));
	}

	@PatchMapping("/restore")
	public ResponseEntity<List<SalesOrderDto>> restoreOrders(@Valid @RequestBody RestoreSalesOrdersDto dto) {
		List<SalesOrder> salesOrders = salesOrderService.restoreSalesOrders(dto.customerId(), dto.salesOrderIds());
		return ResponseEntity.ok(salesOrders.stream().map(SalesOrderDto::from).collect(Collectors.toList()));
	}

	@PatchMapping("/status")
	public ResponseEntity<SalesOrderDto> changeOrderStatus(@Valid @RequestBody ChangeOrderStatusDto dto) {
		SalesOrder salesOrder = salesOrderService.changeOrderStatus(dto.customerId(), dto.salesOrderId(), dto.orderStatus());
		return ResponseEntity.ok(SalesOrderDto.from(salesOrder));
	}

	@PostMapping("/{estimationId}")
	public ResponseEntity<SalesOrderDto> convertEstimationToOrder(@PathVariable Long estimationId, @Valid @RequestBody ConvertEstimationToOrder dto) {
		SalesOrder salesOrder = salesOrderService.convertEstimationToOrder(estimationId, dto.customerId());
		return ResponseEntity.ok(SalesOrderDto.from(salesOrder));
	}

	@Transactional
	@PostMapping("/estimations")
	public ResponseEntity<SalesOrderDto> createEstimation(@Valid @RequestBody CreateEstimationDto dto) {
		SalesOrder salesOrder = salesOrderService.createEstimation(dto);
		return ResponseEntity.ok(SalesOrderDto.from(salesOrder));
	}

	@GetMapping("/{salesOrderId}")
	public ResponseEntity<SalesOrderDto> retrieveSalesOrderById(@PathVariable Long salesOrderId) {
		SalesOrder salesOrder = salesOrderService.findSalesOrderById(salesOrderId);
		return ResponseEntity.ok(SalesOrderDto.from(salesOrder));
	}

}
