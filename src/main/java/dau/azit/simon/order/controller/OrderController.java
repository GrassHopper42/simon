package dau.azit.simon.order.controller;

import dau.azit.simon.order.dto.OrderDto;
import dau.azit.simon.order.service.OrderService;
import lombok.RequiredArgsConstructor;
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
	public String createFirstOrder(@RequestBody() OrderDto.CreateFirstOrderDto createFirstOrderDto) {
		return orderService.createFirstOrder(createFirstOrderDto).toString();
	}

}
