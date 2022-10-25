package ftn.uns.ac.rs.bloodbank.systemAdministrator;

import org.springframework.stereotype.Service;

import java.util.List;

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
