package dau.azit.simon.order.repository;


import dau.azit.simon.order.domain.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<SalesOrder, Long> {

}
