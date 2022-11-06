package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import ftn.uns.ac.rs.bloodbank.center.Center;
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
}
