package ftn.uns.ac.rs.bloodbank.center.repository;

import ftn.uns.ac.rs.bloodbank.center.model.CenterEquipment;
import ftn.uns.ac.rs.bloodbank.center.model.Equipment;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CenterEquipmentRepository extends JpaRepository<CenterEquipment, UUID> {
	@Query("SELECT ce from CenterEquipment ce where ce.center.id =?1 and ce.equipment.name =?2")
	Optional<CenterEquipment> getCenterEquipmentByCenterIdAndEquipmentId(UUID centerId,String equipmentName);
	@Query("SELECT ce.equipment from CenterEquipment ce where ce.center.id =?1")
	List<Equipment> getCenterEquipmentByCenterId(UUID centerId);
}