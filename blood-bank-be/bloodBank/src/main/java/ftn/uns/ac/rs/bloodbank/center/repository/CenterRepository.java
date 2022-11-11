package ftn.uns.ac.rs.bloodbank.center.repository;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CenterRepository extends JpaRepository<Center, UUID> {
    @Query("SELECT c from Center c where c.name = ?1")
    Optional<Center> GetByName(String name);
    @Query("SELECT c.medicalStuff from Center c where c.id =?1")
    List<CenterAdministrator> GetAdmins(UUID id);

}
