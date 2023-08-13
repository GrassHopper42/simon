package dau.azit.simon.product.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateProductDto(@NotBlank String location, String comment) {
}
