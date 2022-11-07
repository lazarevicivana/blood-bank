package ftn.uns.ac.rs.bloodbank.applicationUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID>{

    @Query("Select user from ApplicationUser user where user.username = ?1")
    Optional<ApplicationUser> GetByUsername(String name);

}

