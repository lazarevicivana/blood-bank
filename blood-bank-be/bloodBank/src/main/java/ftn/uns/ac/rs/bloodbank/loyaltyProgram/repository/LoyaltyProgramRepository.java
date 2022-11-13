package ftn.uns.ac.rs.bloodbank.loyaltyProgram.repository;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.loyaltyProgram.model.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Repository
@Transactional(readOnly=true)
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, UUID> {

    @Query("Select program from LoyaltyProgram program where program.customer.id = ?1")
    Optional<LoyaltyProgram> getByCustomerId(UUID customerId);

}
