package dau.azit.simon;

import dau.azit.simon.employee.entity.Employee;
import dau.azit.simon.employee.entity.UserRole;
import dau.azit.simon.employee.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        employeeRepository.save(new Employee(
                UserRole.ADMIN, "admin", null, null, null,
                "admin", passwordEncoder.encode("admin"))
        );
    }

}
