package dau.azit.simon.estimation.service;

import dau.azit.simon.customer.domain.Customer;
import dau.azit.simon.customer.service.CustomerService;
import dau.azit.simon.estimation.domain.Estimation;
import dau.azit.simon.estimation.domain.EstimationLine;
import dau.azit.simon.estimation.dto.request.CreateEstimationDto;
import dau.azit.simon.estimation.repository.EstimationRepository;
import dau.azit.simon.product.domain.Product;
import dau.azit.simon.product.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EstimationService {
	private final EstimationRepository estimationRepository;
	private final CustomerService customerService;
	private final ProductJpaRepository productRepository;

	@Transactional
	public void createEstimation(CreateEstimationDto dto) {
		Customer customer = customerService.findCustomerById(dto.customerId());
		List<EstimationLine> estimationLines = dto.estimationLines().stream()
				.map(estimationLineDto -> {
					Product product = productRepository.findByCode(estimationLineDto.productCode()).orElseThrow();
					return new EstimationLine(product, estimationLineDto.quantity(), estimationLineDto.publicProductName(), estimationLineDto.salesPrice());
				})
				.collect(Collectors.toList());

		Estimation estimation = Estimation.createEstimation(estimationLines, customer, dto.memo().orElse(null));
		estimationRepository.save(estimation);

	}

}
