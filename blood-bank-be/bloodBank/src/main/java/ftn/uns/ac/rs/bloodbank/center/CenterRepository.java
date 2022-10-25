package ftn.uns.ac.rs.bloodbank.center;

import ftn.uns.ac.rs.bloodbank.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CenterRepository extends JpaRepository<Center, UUID> {
    @Query("SELECT c from Center c where c.name = ?1")
    Center GetByName(String name);

}
