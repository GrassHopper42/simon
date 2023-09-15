package dau.azit.simon.employee.dto.response;

import dau.azit.simon.employee.domain.Employee;
import dau.azit.simon.employee.domain.UserRole;

import java.util.UUID;

public record EmployeeCheckDto(
        UUID uuid,
        UserRole role,
        String name,
        String address,
        String description,
        String phoneNumber
) {

    public static EmployeeCheckDto from(Employee employee) {
        return new EmployeeCheckDto(
                employee.getUid(),
                employee.getRole(),
                employee.getName(),
                employee.getAddress(),
                employee.getDescription(),
                employee.getPhoneNumber()
        );
    }

}
