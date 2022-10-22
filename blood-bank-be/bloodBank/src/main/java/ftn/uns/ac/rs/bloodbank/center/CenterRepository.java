package ftn.uns.ac.rs.bloodbank.center;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CenterRepository extends JpaRepository<Center, UUID> {

}
