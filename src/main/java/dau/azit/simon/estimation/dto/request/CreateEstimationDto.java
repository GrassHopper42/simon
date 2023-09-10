package dau.azit.simon.estimation.dto.request;

import java.util.List;
import java.util.Optional;

public record CreateEstimationDto(
		Long customerId,

		List<CreateEstimationLineDto> estimationLines,

		String deliveryAddress,

		Optional<String> memo

) {
}