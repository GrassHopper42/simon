package dau.azit.simon.employee.dto;

public record EmployeeCreateRequestDto(
        String role,
        String name,
        String address,
        String status,
        String description,
        String phoneNumber,
        String password
) {}
