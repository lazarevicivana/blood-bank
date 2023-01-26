package ftn.uns.ac.rs.bloodbank.blood.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BloodContractRepository extends JpaRepository<BloodContract, Long> {
    @Query("SELECT bc from BloodContract bc where bc.isExpired = false and bc.hospitalName=?1")
    Optional<BloodContract> getCurrentContract(String hospitalName);
}