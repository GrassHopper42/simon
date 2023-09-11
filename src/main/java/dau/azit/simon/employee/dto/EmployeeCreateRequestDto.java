package dau.azit.simon.employee.dto;

public record EmployeeCreateRequestDto(
        String role,
        String name,
        String address,
        String description,
        String phoneNumber,
        String password
) {}
