package dau.azit.simon.employee.service;

import dau.azit.simon.employee.entity.Employee;
import dau.azit.simon.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Employee createOne(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateOne(Employee employee) {
        return null;
    }

    @Transactional
    public void removeOne(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

}
