package dau.azit.simon.employee.controller;

import dau.azit.simon.employee.dto.EmployeeCreateRequestDto;
import dau.azit.simon.employee.dto.EmployeeUpdateRequestDto;
import dau.azit.simon.employee.entity.Employee;
import dau.azit.simon.employee.service.EmployeeService;
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

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<Employee>> employeeList(Pageable pageable) {
        return ResponseEntity.ok(employeeService.findAllEmployee(pageable));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Employee> employeeDetails(@PathVariable UUID uid) {
        return ResponseEntity.ok(employeeService.findOneByUid(uid));
    }

    @PostMapping
    public ResponseEntity<Employee> employeeAdd(@RequestBody EmployeeCreateRequestDto dto) {
        Employee employee = employeeService.addOne(dto);
        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        return ResponseEntity.created(URI.create(currentUri + "/" + employee.getUid())).body(employee);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Employee> employeeModify(@PathVariable UUID uid, @RequestBody EmployeeUpdateRequestDto dto) {
        return ResponseEntity.ok(employeeService.modifyOne(uid, dto));
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> employeeRemove(@PathVariable UUID uid) {
        employeeService.removeOneByUid(uid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
