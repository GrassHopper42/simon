package dau.azit.simon.employee.repository;

import dau.azit.simon.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUid(UUID uid);

    Optional<Employee> findByPhoneNumber(String phoneNumber);

    void deleteByUid(UUID uid);

}
