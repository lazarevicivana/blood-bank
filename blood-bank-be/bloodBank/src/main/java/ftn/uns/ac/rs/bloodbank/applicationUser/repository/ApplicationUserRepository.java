package ftn.uns.ac.rs.bloodbank.applicationUser.repository;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
    Optional<ApplicationUser> findByUsername(String username);
    @Query("Select user from ApplicationUser user where user.username = ?1")
    Optional<ApplicationUser> getByUsername(String name);
    @Transactional
    @Modifying
    @Query("UPDATE ApplicationUser a " +
            "SET a.enabled = TRUE WHERE a.username = ?1")
    int enableAppUser(String username);
}



