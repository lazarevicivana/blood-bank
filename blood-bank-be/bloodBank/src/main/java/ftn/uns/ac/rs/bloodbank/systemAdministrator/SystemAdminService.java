package ftn.uns.ac.rs.bloodbank.systemAdministrator;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SystemAdminService {
    private final SystemAdminRepository systemAdminRepository;

    public SystemAdminService(SystemAdminRepository systemAdminRepository) {
        this.systemAdminRepository = systemAdminRepository;
    }

    public void createAdmin(SystemAdministrator  systemAdministrator) {
        systemAdminRepository.save(systemAdministrator);
    }

    public List<SystemAdministrator> getAllAdmins() {
        return systemAdminRepository.findAll();
    }

}
