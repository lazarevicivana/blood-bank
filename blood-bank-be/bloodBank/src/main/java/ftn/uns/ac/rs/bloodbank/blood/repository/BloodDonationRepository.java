package ftn.uns.ac.rs.bloodbank.blood.repository;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BloodDonationRepository extends JpaRepository<BloodDonation, UUID> {
}