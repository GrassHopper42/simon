package dau.azit.simon.employee.controller;

import dau.azit.simon.config.SimonConfigProperties;
import dau.azit.simon.employee.dto.request.EmployeeCreateDto;
import dau.azit.simon.employee.dto.request.EmployeeLoginDto;
import dau.azit.simon.employee.dto.request.EmployeeUpdateDto;
import dau.azit.simon.employee.dto.response.EmployeeCheckDto;
import dau.azit.simon.employee.domain.Employee;
import dau.azit.simon.employee.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final SimonConfigProperties properties;
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeCheckDto>> employeeList(Pageable pageable) {
        Page<Employee> employeePage = employeeService.findAllEmployee(pageable);
        return ResponseEntity.ok(employeePage.map(EmployeeCheckDto::from));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<EmployeeCheckDto> employeeDetails(@PathVariable UUID uid) {
        Employee employee = employeeService.findOneByUid(uid);
        return ResponseEntity.ok(EmployeeCheckDto.from(employee));
    }

    @PostMapping
    public ResponseEntity<EmployeeCheckDto> employeeRegister(@RequestBody EmployeeCreateDto dto) {
        Employee employee = employeeService.register(dto);
        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        return ResponseEntity.created(URI.create(currentUri + "/" + employee.getUid())).body(EmployeeCheckDto.from(employee));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpSession session, @RequestBody EmployeeLoginDto dto) {
        employeeService.login(dto);
        session.setAttribute(properties.getKey(), employeeService.findOneByPhoneNumber(dto.phoneNumber()));
        return ResponseEntity.ok("Authentication Success");
    }

    @PutMapping("/{uid}")
    public ResponseEntity<EmployeeCheckDto> employeeModify(@PathVariable UUID uid, @RequestBody EmployeeUpdateDto dto) {
        Employee employee = employeeService.modifyOne(uid, dto);
        return ResponseEntity.ok(EmployeeCheckDto.from(employee));
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> employeeRemove(@PathVariable UUID uid) {
        employeeService.removeOneByUid(uid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
