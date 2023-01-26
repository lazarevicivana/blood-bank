package ftn.uns.ac.rs.bloodbank.customer.repository;

import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.customer.model.CustomerQRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerQRCodeRepository extends JpaRepository<CustomerQRCode, UUID> {

    @Query("SELECT c from Customer_qr_code c where c.customer.id = ?1")
    List<CustomerQRCode> getByCustomer(UUID customerId);
}
