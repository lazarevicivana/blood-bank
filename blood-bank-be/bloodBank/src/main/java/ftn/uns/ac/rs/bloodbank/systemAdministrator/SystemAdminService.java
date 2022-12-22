package ftn.uns.ac.rs.bloodbank.systemAdministrator;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiConflictException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import ftn.uns.ac.rs.bloodbank.systemAdministrator.dto.SystemAdministratorDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SystemAdminService {
    private final SystemAdminRepository systemAdminRepository;
    private final MapperService mapperService;
    private PasswordEncoder encoder;


    @Transactional
    public void createAdmin(SystemAdministratorDto systemAdministratorDto) {
        SystemAdministrator systemAdministrator = mapperService.SystemAdministratorDtoToSystemAdministrator(systemAdministratorDto);
        var adminExist = systemAdminRepository.GetByUsername(systemAdministratorDto.getUsername());
        if(adminExist.isPresent()){
            throw  new ApiConflictException("This username is already taken");
        }
        systemAdministrator.setPassword(hashPassword(systemAdministrator.getPassword()));
        systemAdminRepository.save(systemAdministrator);
    }

    public List<SystemAdministrator> getAllAdmins() {
        return systemAdminRepository.findAll();
    }

    public String hashPassword(String userPassword){
        var encodedPassword = encoder.encode(userPassword);
        return encodedPassword;
    }
}
