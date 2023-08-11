package dau.azit.simon.employee.controller.dto;

import lombok.Getter;

@Getter
public class EmployeeUpdateRequestDto {

    private String name;
    private String address;
    private String status;
    private String description;

}
