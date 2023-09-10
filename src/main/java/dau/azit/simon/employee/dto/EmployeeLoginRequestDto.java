package dau.azit.simon.employee.dto;

public record EmployeeLoginRequestDto(
        String phoneNumber,
        String password
) {
}
