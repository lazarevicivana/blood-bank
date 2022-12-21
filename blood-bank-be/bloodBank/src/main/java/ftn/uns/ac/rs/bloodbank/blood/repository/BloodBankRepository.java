package ftn.uns.ac.rs.bloodbank.blood.repository;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BloodBankRepository extends JpaRepository<BloodBank, UUID> {
}