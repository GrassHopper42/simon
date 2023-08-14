package dau.azit.simon.employee.service;

import dau.azit.simon.employee.dto.EmployeeCreateRequestDto;
import dau.azit.simon.employee.dto.EmployeeUpdateRequestDto;
import dau.azit.simon.employee.entity.Employee;
import dau.azit.simon.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> findAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee findOneById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow();
    }

    public Employee findOneByUid(UUID uid) {
        return employeeRepository.findByUid(uid).orElseThrow();
    }

    @Transactional
    public Employee addOne(EmployeeCreateRequestDto dto) {
        return employeeRepository.save(new Employee(
                dto.name(),
                dto.address(),
                dto.status(),
                dto.description()
        ));
    }

    @Transactional
    public Employee modifyOne(UUID uid, EmployeeUpdateRequestDto dto) {
        Employee employee = findOneByUid(uid);
        employee.updateField(dto);
        return employee;
    }

    @Transactional
    public void removeOne(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void removeOneByUid(UUID uid) {
        employeeRepository.deleteByUid(uid);
    }

}
