package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CenterAdminRepository extends JpaRepository<CenterAdministrator, UUID> {
    @Query("select ca.center from CenterAdministrator ca where ca.id=?1")
   Center GetAdminCenter(UUID adminID);

    @Query("select c from CenterAdministrator c where c.center is null ")
    List<CenterAdministrator> GetAvailableAdmins();
}
