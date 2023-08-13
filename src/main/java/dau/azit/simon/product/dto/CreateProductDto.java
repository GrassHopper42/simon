package dau.azit.simon.product.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record CreateProductDto(@NotNull @Max(15) String code, @NotNull String name) {
}
