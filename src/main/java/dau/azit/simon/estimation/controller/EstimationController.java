package dau.azit.simon.estimation.controller;


import dau.azit.simon.estimation.dto.request.CreateEstimationDto;
import dau.azit.simon.estimation.service.EstimationService;
import dau.azit.simon.order.domain.SalesOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/estimations")
public class EstimationController {
	private final EstimationService estimationService;

	@PostMapping()
	public ResponseEntity<SalesOrder> createEstimation(@Valid @RequestBody() CreateEstimationDto dto) {
		estimationService.createEstimation(dto);
		return ResponseEntity.ok(null);
	}

}
