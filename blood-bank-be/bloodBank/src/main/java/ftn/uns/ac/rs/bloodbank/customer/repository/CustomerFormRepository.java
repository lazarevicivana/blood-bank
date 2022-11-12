package ftn.uns.ac.rs.bloodbank.customer.repository;

import ftn.uns.ac.rs.bloodbank.customer.model.CustomerForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CustomerFormRepository extends JpaRepository<CustomerForm, UUID> {
}
