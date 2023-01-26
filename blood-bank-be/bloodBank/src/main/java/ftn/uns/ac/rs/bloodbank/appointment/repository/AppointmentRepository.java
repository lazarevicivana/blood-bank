package ftn.uns.ac.rs.bloodbank.appointment.repository;

import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query("SELECT c.availableAppointments from Center c where c.id = ?1")
    List<Appointment> getAllAppointmentsForCenter(UUID centerId);
    @Query("SELECT app.medicalStaffs from Appointment app where app.id = ?1 and app.deleted = false")
    List<CenterAdministrator> getMedicalStaffsForAppointment(UUID appointmentId);

    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value ="0") })
    @Query("SELECT a FROM Appointment a WHERE a.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Appointment performLock(UUID id);

}