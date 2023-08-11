package dau.azit.simon.employee.entity;

import dau.azit.simon.employee.controller.dto.EmployeeUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID uid;

    private String name;

    private String address;

    private String status;

    private String description;

    public Employee(String name, String address, String status, String desc) {
        this.uid = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.status = status;
        this.description = desc;
    }

    public void updateFiled(EmployeeUpdateRequestDto dto) {
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.status = dto.getStatus();
        this.description = dto.getDescription();
    }

}
