package dau.azit.simon.employee.service;

import dau.azit.simon.employee.dto.request.EmployeeCreateDto;
import dau.azit.simon.employee.dto.request.EmployeeLoginDto;
import dau.azit.simon.employee.dto.request.EmployeeUpdateDto;
import dau.azit.simon.employee.domain.Employee;
import dau.azit.simon.employee.domain.UserRole;
import dau.azit.simon.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Page<Employee>   findAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee findOneById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow();
    }

    public Employee findOneByUid(UUID uid) {
        return employeeRepository.findByUid(uid).orElseThrow();
    }

    public Employee findOneByPhoneNumber(String phoneNumber) {
        return employeeRepository.findByPhoneNumber(phoneNumber).orElseThrow();
    }

    public void login(EmployeeLoginDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.phoneNumber(), dto.password());
        authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }

    @Transactional
    public Employee register(EmployeeCreateDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());
        Employee employee = new Employee(
                UserRole.valueOf(dto.role().toUpperCase()),
                dto.name(),
                dto.address(),
                dto.description(),
                dto.phoneNumber(),
                encodedPassword
        );
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee modifyOne(UUID uid, EmployeeUpdateDto dto) {
        Employee employee = findOneByUid(uid);
        employee.updateField(dto.name(), dto.address(), dto.description());
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
