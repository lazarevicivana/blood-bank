package ftn.uns.ac.rs.bloodbank.systemAdministrator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdministrator, UUID> {
}
