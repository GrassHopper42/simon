package dau.azit.simon.employee.dto;

import lombok.Getter;

public record EmployeeCreateRequestDto(
        String name,
        String address,
        String status,
        String description
) {}
