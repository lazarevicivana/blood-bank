package ftn.uns.ac.rs.bloodbank.appointment.repository;

import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query("SELECT c.availableAppointments from Center c where c.id = ?1")
    List<Appointment> getAllAppointmentsForCenter(UUID centerId);
    @Query("SELECT ap.medicalStaffs from Appointment ap where ap.id = ?1")
    List<CenterAdministrator> getMedicalStaffsForAppointment(UUID appointmentId);
}