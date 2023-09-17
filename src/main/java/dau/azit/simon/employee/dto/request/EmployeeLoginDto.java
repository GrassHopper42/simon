package dau.azit.simon.employee.dto.request;

public record EmployeeLoginDto(
        String phoneNumber,
        String password
) {
}
