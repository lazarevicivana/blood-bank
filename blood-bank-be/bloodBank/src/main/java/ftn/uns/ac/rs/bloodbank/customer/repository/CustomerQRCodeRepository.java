package ftn.uns.ac.rs.bloodbank.customer.repository;

import ftn.uns.ac.rs.bloodbank.customer.model.CustomerQRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CustomerQRCodeRepository extends JpaRepository<CustomerQRCode, UUID> {
}
