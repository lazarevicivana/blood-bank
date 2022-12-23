package ftn.uns.ac.rs.bloodbank.blood.repository;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodBank;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BloodBankRepository extends JpaRepository<BloodBank, UUID> {
    @Query("SELECT b from BloodBank b where b.center.id =?1 and b.bloodType = ?2")
    Optional<BloodBank> getBankByCenterAndBloodType(UUID centerId, BloodType bloodType);
    @Query("SELECT b from BloodBank b where b.center.id =?1")
    List<BloodBank> getBanksByCenterId(UUID centerId);
}