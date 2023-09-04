package dau.azit.simon.product.dto;

import org.springframework.lang.Nullable;

public record CreateSupplierDto(String name, @Nullable String contact) {
}
