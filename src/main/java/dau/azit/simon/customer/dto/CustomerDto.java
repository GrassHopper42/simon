package dau.azit.simon.customer.dto;

public class CustomerDto {
	public record CreateCustomerDto(String name, String contact, String companyName, String companyAddress, String companyRepresentative) {
	}
}
