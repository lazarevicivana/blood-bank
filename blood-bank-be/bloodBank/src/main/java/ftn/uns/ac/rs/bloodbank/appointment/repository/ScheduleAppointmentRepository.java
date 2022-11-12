package ftn.uns.ac.rs.bloodbank.appointment.repository;

import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.customer.Customer;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleAppointmentRepository extends JpaRepository<ScheduleAppointment, UUID> {

    @Query("SELECT sa.customer from ScheduleAppointment sa where sa.appointment.center.id = ?1 and sa.appointment.date <= ?2")
    List<Customer> GetCenterDonors(UUID center, LocalDateTime date);

    @Query("SELECT DISTINCT sa.customer from ScheduleAppointment sa where sa.appointment.center.id = ?1 and sa.appointment.date <= ?2 and LOWER(sa.customer.name) like ?3% and LOWER(sa.customer.surname) like ?4%")
    List<Customer> SearchCenterDonors(UUID center, LocalDateTime date, String name, String surname);

    @Query("SELECT sa.customer from ScheduleAppointment sa where sa.appointment.date <= ?1")
    List<Customer> GetDonors(LocalDateTime date);

    @Query("SELECT DISTINCT sa.customer from ScheduleAppointment sa where sa.appointment.date <= ?1 and LOWER(sa.customer.name) like ?2% and LOWER(sa.customer.surname) like ?3%")
    List<Customer> SearchDonors(LocalDateTime date, String name, String surname);
}
