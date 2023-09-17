package dau.azit.simon.order.service;

import dau.azit.simon.customer.domain.Customer;
import dau.azit.simon.customer.service.CustomerService;
import dau.azit.simon.order.domain.OrderStatus;
import dau.azit.simon.order.domain.SalesOrder;
import dau.azit.simon.order.domain.SalesOrderLine;
import dau.azit.simon.order.dto.request.CreateEstimationDto;
import dau.azit.simon.order.dto.request.CreateSalesOrderDto;
import dau.azit.simon.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SalesOrderService {
	private final OrderRepository orderRepository;
	private final CustomerService customerService;

	@Transactional
	public SalesOrder createSalesOrder(CreateSalesOrderDto dto) {
		Customer customer = customerService.findCustomerById(dto.customerId());
		List<SalesOrderLine> orderLines = dto.salesOrderLines().stream()
				.map(salesOrderLine ->
					 new SalesOrderLine(salesOrderLine.productId(), salesOrderLine.quantity(), salesOrderLine.publicProductName(), salesOrderLine.salesPrice()))
				.collect(Collectors.toList());

		SalesOrder order = SalesOrder.createOrder(orderLines, customer, dto.orderStatus(), dto.memo().orElse(null));

		return orderRepository.save(order);
	}

	@Transactional
	public List<SalesOrder> cancelSalesOrders(Long customerId, List<Long> orderIds) {
		List<SalesOrder> orders = orderRepository.findByCustomerIdAndIdIn(customerId, orderIds);
		orders.forEach(SalesOrder::cancel);
		return orders;
	}

	@Transactional
	public List<SalesOrder> restoreSalesOrders(Long customerId, List<Long> orderIds) {
		List<SalesOrder> orders = orderRepository.findByCustomerIdAndIdIn(customerId, orderIds);
		orders.forEach(SalesOrder::restore);
		return orders;
	}

	@Transactional
	public SalesOrder changeOrderStatus(Long customerId, Long salesOrderId, OrderStatus orderStatus) {
		SalesOrder order = orderRepository.findByCustomerIdAndId(customerId, salesOrderId).orElseThrow(() -> new IllegalArgumentException("order not found"));
		order.changeStatus(orderStatus);
		return order;
	}

	public SalesOrder createEstimation(CreateEstimationDto dto) {
		Customer customer = customerService.findCustomerById(dto.customerId());
		List<SalesOrderLine> orderLines = dto.salesOrderLines().stream()
				.map(salesOrderLine ->
						new SalesOrderLine(salesOrderLine.productId(), salesOrderLine.quantity(), salesOrderLine.publicProductName(), salesOrderLine.salesPrice()))
				.collect(Collectors.toList());

		SalesOrder order = SalesOrder.createOrder(orderLines, customer, OrderStatus.ESTIMATION, dto.memo().orElse(null));

		return orderRepository.save(order);
	}

	public SalesOrder findSalesOrderById(Long salesOrderId) {
		return orderRepository.findById(salesOrderId).orElseThrow(() -> new IllegalArgumentException("order not found"));
	}
}
