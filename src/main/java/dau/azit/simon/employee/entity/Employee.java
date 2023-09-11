package dau.azit.simon.employee.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
@Where(clause = "deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE id = ?")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID uid;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String name;

    private String address;

    private String description;

    @Column(unique = true)
    private String phoneNumber;

    private String password;

    private boolean deleted;

    public Employee(UserRole role, String name, String address, String description, String phoneNumber, String password) {
        this.uid = UUID.randomUUID();
        this.role = role;
        this.name = name;
        this.address = address;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public void updateField(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

}
