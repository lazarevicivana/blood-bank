package ftn.uns.ac.rs.bloodbank.center.service;

import ftn.uns.ac.rs.bloodbank.center.model.Equipment;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterEquipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CenterEquipmentService {
	private final CenterEquipmentRepository centerEquipmentRepository;
	public List<Equipment> getCenterEquipmentByCenterId(UUID centerId){
		return centerEquipmentRepository.getCenterEquipmentByCenterId(centerId);
	}
}
