package dau.azit.simon.estimation.dto.request;

public record CreateEstimationLineDto(
		String productCode,

		String publicProductName,

		int quantity,

		int salesPrice
) {
}
