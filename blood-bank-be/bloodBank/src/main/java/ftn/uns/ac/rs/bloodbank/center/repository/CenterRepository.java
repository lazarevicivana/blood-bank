package ftn.uns.ac.rs.bloodbank.center.repository;

import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
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
    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value ="0") })
    @Query("SELECT c FROM Center c WHERE c.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Center performLock(UUID id);

}
