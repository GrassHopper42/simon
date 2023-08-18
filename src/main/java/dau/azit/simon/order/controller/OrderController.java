package dau.azit.simon.order.controller;


import dau.azit.simon.order.domain.SalesOrder;
import dau.azit.simon.order.dto.OrderDto;
import dau.azit.simon.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/first")
	public ResponseEntity<SalesOrder> createFirstOrder(@Valid @RequestBody() OrderDto.CreateFirstOrderDto createFirstOrderDto) {
		return ResponseEntity.ok(orderService.createFirstOrder(createFirstOrderDto));
	}

	@PostMapping("/additional")
	public ResponseEntity<SalesOrder> createAdditionalOrder(@Valid @RequestBody() OrderDto.CreateAdditionalOrderDto dto) {
		orderService.createAdditionalOrder(dto);
		return ResponseEntity.ok(null);
	}
}
