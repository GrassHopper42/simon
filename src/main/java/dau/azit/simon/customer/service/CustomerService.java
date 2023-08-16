package dau.azit.simon.customer.service;

import dau.azit.simon.customer.domain.Customer;
import dau.azit.simon.customer.dto.CustomerDto;
import dau.azit.simon.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CustomerService {
	private final CustomerRepository customerRepository;

	@Transactional
	public Customer createCustomer(CustomerDto.CreateCustomerDto dto) {
		Customer customer = Customer.createCustomer(dto.name(), dto.contact(), dto.companyName(), dto.companyAddress(), dto.companyRepresentative());
		return customerRepository.save(customer);
	}

	public Customer findCustomerById(Long customerId) {
		return customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("customer not found"));
	}
}
