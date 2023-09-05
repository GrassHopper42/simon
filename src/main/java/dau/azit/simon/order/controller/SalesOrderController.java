package dau.azit.simon.order.controller;


import dau.azit.simon.order.domain.SalesOrder;
import dau.azit.simon.order.dto.request.CreateSalesOrderDto;
import dau.azit.simon.order.service.SalesOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
