package dau.azit.simon.order.service;

import dau.azit.simon.order.domain.Order;
import dau.azit.simon.order.domain.OrderLine;
import dau.azit.simon.order.domain.mock.Customer;
import dau.azit.simon.order.domain.mock.Product;
import dau.azit.simon.order.dto.OrderDto;
import dau.azit.simon.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
	private final OrderRepository orderRepository;

	public Order createOrder(OrderDto.CreateOrderDto dto) {
		//TODO : 실제 Customer 클래스로 수정 필요
		Customer customer = new Customer();

		List<OrderLine> orderLines = dto.orderLines().stream()
				.map(orderLineDto -> {
					//TODO : 실제 Product 클래스로 수정 필요
					Product product = new Product();
					return new OrderLine(product, orderLineDto.getQuantity());
				})
				.collect(Collectors.toList());

		Order order = Order.createOrder(orderLines, customer, dto.memo().orElse(null));

		return orderRepository.save(order);
	}

	public Order createEstimation(OrderDto.CreateOrderDto dto) {
		//TODO : 실제 Customer 클래스로 수정 필요
		Customer customer = new Customer();

		List<OrderLine> orderLines = dto.orderLines().stream()
				.map(orderLineDto -> {
					//TODO : 실제 Product 클래스로 수정 필요
					Product product = new Product();
					return new OrderLine(product, orderLineDto.getQuantity());
				})
				.collect(Collectors.toList());

		Order order = Order.createEstimation(orderLines, customer, dto.memo().orElse(null));

		return orderRepository.save(order);
	}

}
