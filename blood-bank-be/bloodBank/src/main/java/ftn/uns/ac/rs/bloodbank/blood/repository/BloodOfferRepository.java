package ftn.uns.ac.rs.bloodbank.blood.repository;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodContract;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodOfferRepository extends JpaRepository<BloodOffer, Long> {
    @Query("SELECT offer  from BloodOffer offer where offer.bloodContract.id = ?1")
    List<BloodOffer> getOfferForContract(Long contractId);
}