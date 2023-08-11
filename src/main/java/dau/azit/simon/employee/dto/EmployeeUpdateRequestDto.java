package dau.azit.simon.employee.dto;

import lombok.Getter;

public record EmployeeUpdateRequestDto(
        String name,
        String address,
        String status,
        String description
) {}
