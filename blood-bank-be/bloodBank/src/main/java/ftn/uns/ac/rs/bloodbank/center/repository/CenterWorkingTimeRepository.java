package ftn.uns.ac.rs.bloodbank.center.repository;

import ftn.uns.ac.rs.bloodbank.center.model.CenterWorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CenterWorkingTimeRepository extends JpaRepository<CenterWorkingTime, UUID> {


}
