package dau.azit.simon.order.repository;


import dau.azit.simon.order.domain.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<SalesOrder, Long> {
	List<SalesOrder> findByCustomerIdAndIdIn(Long customerId, List<Long> orderIds);

	Optional<SalesOrder> findByCustomerIdAndId(Long customerId, Long salesOrderId);
}
