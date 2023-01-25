package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.dto.CenterAdministratorDto;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiConflictException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CenterAdminService {
    private final CenterAdminRepository centerAdminRepository;
    private final CenterRepository centerRepository;
    private final MapperService mapperService;
    private PasswordEncoder encoder;
    @Cacheable("center-for-admin")
    public Center GetAdminCenter(UUID adminID){
        return centerAdminRepository.GetAdminCenter(adminID);
    }

    public CenterAdministrator getCenterAdministrator(UUID id) {
        return centerAdminRepository
                .findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Center administrator with id: " + id + "does not exist"));
    }
    @Transactional
    public void createCenterAdministrator(CenterAdministratorDto centerAdministratorDto) {
        CenterAdministrator centerAdministrator = mapperService.CenterAdministratorDtoToCenterAdministrator(centerAdministratorDto);
        var adminExist = centerAdminRepository.GetByUsername(centerAdministratorDto.getUsername());
        if(adminExist.isPresent()){
            throw  new ApiConflictException("This username is already taken");
        }
        var center = centerRepository.findById(centerAdministratorDto.getCenter());
        if(center.isPresent()){
            center.get().addAdmin(centerAdministrator);
            centerAdminRepository.save(centerAdministrator);
        }
        centerAdministrator.setPassword(hashPassword(centerAdministratorDto.getPassword()));
        centerAdminRepository.save(centerAdministrator);
        //return centerAdministrator;
    }

    @Transactional
    public void updateAdministratorCenter(UUID adminId, Center center)
    {
        var admin = getCenterAdministrator(adminId);
        admin.setCenter(center);
    }
    @Cacheable("available-admins")
    public List<CenterAdministrator> getAvailableAdmins(){
        return centerAdminRepository.GetAvailableAdmins();
    }

    public String hashPassword(String userPassword){
        var encodedPassword = encoder.encode(userPassword);
        return encodedPassword;
    }
}
