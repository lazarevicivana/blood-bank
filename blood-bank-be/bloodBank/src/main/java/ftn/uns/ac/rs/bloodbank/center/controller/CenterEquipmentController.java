package ftn.uns.ac.rs.bloodbank.center.controller;

import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentExaminationDto;
import ftn.uns.ac.rs.bloodbank.center.model.Equipment;
import ftn.uns.ac.rs.bloodbank.center.service.CenterEquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/center-equipment")
@AllArgsConstructor
public class CenterEquipmentController {
	private final CenterEquipmentService centerEquipmentService;

	@GetMapping(path = "/{centerId}")
	public ResponseEntity<List<Equipment>> getCentersEquipment(@PathVariable("centerId") @NotNull UUID centerId){
		var equipments = centerEquipmentService.getCenterEquipmentByCenterId(centerId);
		return ResponseEntity.ok(equipments);
	}
}
