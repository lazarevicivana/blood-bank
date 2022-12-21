package ftn.uns.ac.rs.bloodbank.customer.repository;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.customer.model.CustomerForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CustomerFormRepository extends JpaRepository<CustomerForm, UUID> {
    @Query("SELECT cf from CustomerForm cf where cf.customer = ?1")
    List<CustomerForm> findByCustomerId(UUID customerId);
}
