package ftn.uns.ac.rs.bloodbank.systemAdministrator;

import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdministrator, UUID> {
    @Query("SELECT ca from SystemAdministrator ca where ca.username = ?1")
    Optional<SystemAdministrator> GetByUsername(String username);
}
