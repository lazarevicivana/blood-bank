package ftn.uns.ac.rs.bloodbank.systemAdministrator;

import ftn.uns.ac.rs.bloodbank.systemAdministrator.dto.SystemAdministratorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/systemAdmins")
public class SystemAdminController {
    private final SystemAdminService systemAdminService;

    public SystemAdminController(SystemAdminService systemAdminService) {
        this.systemAdminService = systemAdminService;
    }
    @PostMapping()
    public  ResponseEntity<String> createAdmin(@RequestBody SystemAdministratorDto systemAdministratorDto){
        systemAdminService.createAdmin(systemAdministratorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping()
    public List<SystemAdministrator> getAllAdmins(){
        return systemAdminService.getAllAdmins();
    }

}
