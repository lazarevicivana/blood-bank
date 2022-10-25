package ftn.uns.ac.rs.bloodbank.systemAdministrator;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/systemAdmins")
public class SystemAdminController {
    private final SystemAdminService systemAdminService;

    public SystemAdminController(SystemAdminService systemAdminService) {
        this.systemAdminService = systemAdminService;
    }
    @PostMapping
    public void createAdmin(@RequestBody SystemAdministrator systemAdministrator){
        systemAdminService.createAdmin(systemAdministrator);
    }
    @GetMapping
    public List<SystemAdministrator> getAllAdmins(){
        return systemAdminService.getAllAdmins();
    }

}
