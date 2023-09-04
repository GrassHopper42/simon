package dau.azit.simon.product.dto;

public record UpdateProductDto(
        String name,
        String location,
        Integer price,
        String description
) {}
