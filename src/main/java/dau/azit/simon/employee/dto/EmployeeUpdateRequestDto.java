package dau.azit.simon.employee.dto;

public record EmployeeUpdateRequestDto(
        String name,
        String address,
        String status,
        String description
) {}
