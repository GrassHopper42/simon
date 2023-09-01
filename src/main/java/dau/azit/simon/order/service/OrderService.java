package dau.azit.simon.order.service;

import dau.azit.simon.customer.domain.Customer;
import dau.azit.simon.customer.service.CustomerService;
import dau.azit.simon.order.domain.SalesOrder;
import dau.azit.simon.order.domain.OrderLine;
import dau.azit.simon.order.dto.OrderDto;
import dau.azit.simon.order.repository.OrderRepository;
import dau.azit.simon.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {
	private final OrderRepository orderRepository;
	private final CustomerService customerService;

	@Transactional
	public void createAdditionalOrder(OrderDto.CreateAdditionalOrderDto dto) {

		Customer customer = customerService.findCustomerById(dto.customerId());

		List<OrderLine> orderLines = dto.orderLines().stream()
				.map(orderLineDto -> {
					//TODO : 실제 Product 클래스로 수정 필요
					Product product = new Product();
					return new OrderLine(product, orderLineDto.quantity());
				})
				.collect(Collectors.toList());

		SalesOrder order = SalesOrder.createOrder(orderLines, customer, dto.orderStatus(), dto.memo().orElse(null));

		orderRepository.save(order);
	}
	
}
