package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CenterAdminService {
    private final CenterAdminRepository centerAdminRepository;
    public Center GetAdminCenter(UUID adminID){
        return centerAdminRepository.GetAdminCenter(adminID);
    }

    public CenterAdministrator getCenterAdministrator(UUID id) {
        return centerAdminRepository
                .findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Center administrator with id: " + id + "does not exist"));
    }

    public CenterAdministrator createCenterAdministrator(CenterAdministrator centerAdministrator) {
        centerAdminRepository.save(centerAdministrator);
        return centerAdministrator;
    }

}
