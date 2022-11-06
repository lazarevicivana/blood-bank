package ftn.uns.ac.rs.bloodbank.center;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CenterRepository extends JpaRepository<Center, UUID> {
    @Query("SELECT c from Center c where c.name = ?1")
    Optional<Center> GetByName(String name);

}
