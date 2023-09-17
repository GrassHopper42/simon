package dau.azit.simon.employee.dto.request;

public record EmployeeCreateDto(
        String role,
        String name,
        String address,
        String description,
        String phoneNumber,
        String password
) {}
