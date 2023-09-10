package dau.azit.simon.estimation.repository;


import dau.azit.simon.estimation.domain.Estimation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstimationRepository extends JpaRepository<Estimation, Long> {
}

