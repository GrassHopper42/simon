package dau.azit.simon.employee.entity;

import dau.azit.simon.employee.dto.EmployeeUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Getter
@Entity
@Where(clause = "deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE id = ?")
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

    private boolean deleted;

    public Employee(String name, String address, String status, String desc) {
        this.uid = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.status = status;
        this.description = desc;
    }

    public void updateField(EmployeeUpdateRequestDto dto) {
        this.name = dto.name();
        this.address = dto.address();
        this.status = dto.status();
        this.description = dto.description();
    }

}
