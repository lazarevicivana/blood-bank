package ftn.uns.ac.rs.bloodbank.examination.repository;

import ftn.uns.ac.rs.bloodbank.examination.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ExaminationRepository extends JpaRepository<Examination, UUID> {
}