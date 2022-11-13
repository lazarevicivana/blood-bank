package ftn.uns.ac.rs.bloodbank.center.controller;

import com.sun.istack.NotNull;
import ftn.uns.ac.rs.bloodbank.applicationUser.dto.ApplicationUserDtoResponse;
import ftn.uns.ac.rs.bloodbank.appointment.dto.MedicalStaffResponse;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.dto.CenterAdministratorDto;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDto;
import ftn.uns.ac.rs.bloodbank.center.service.CenterService;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoResponse;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoUpdate;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/center")
public class CenterController {
    private final CenterService centerService;
    private final MapperService mapperService;

    public CenterController(CenterService centerService, MapperService mapperService) {
        this.centerService = centerService;
        this.mapperService = mapperService;
    }

    @GetMapping()
    public ResponseEntity<List<CenterDtoResponse>> getAllCenters(){
        var centers = centerService.getAllCenters()
                .stream()
                .map(mapperService::CenterToCenterDto)
                .toList();
        return ResponseEntity.ok(centers);
    }
    @PostMapping()
    public ResponseEntity<CenterDtoResponse> createCenter(@RequestBody CenterDto centerDto){
        var center = mapperService.CenterDtoToCenter(centerDto);
        var savedCenter = mapperService.CenterToCenterDto(centerService.createCenter(center));
        return new ResponseEntity<CenterDtoResponse>(savedCenter, HttpStatus.CREATED);
    }
//    @PreAuthorize("hasAnyRole('ROLE_CENTER_ADMIN','ROLE_SYSTEM_ADMIN','ROLE_CUSTOMER')")
    @GetMapping(path = "{id}")
    public ResponseEntity<CenterDtoResponse> getCenter(@NotNull @PathVariable("id") UUID id) {
        var c = centerService.getCenter(id);
        var center =mapperService
                .CenterToCenterDto(c);
        return ResponseEntity.ok(center);
    }
    @GetMapping(path = "/other-admins/{centerId}/{adminId}")
    public List<ApplicationUserDtoResponse> getOtherCenterAdmins(@NotNull @PathVariable("centerId") UUID centerId, @NotNull @PathVariable("adminId") UUID adminId){
        var admins = centerService.getOtherCenterAdmins(centerId,adminId);
        return admins.stream()
                .map(mapperService::AppUserToAppUserDto)
                .toList();
    }
    @PutMapping()
    public ResponseEntity<String> updateCenter(@RequestBody CenterDtoUpdate centerDto){
        var center = mapperService.CenterDtoUpdateToCenter(centerDto);
        centerService.updateCenter(center);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/admins/{id}")
    public ResponseEntity<List<MedicalStaffResponse>> getAdminsForCenter(@NotNull @PathVariable("id") UUID id){
        var stuff = centerService.getAdminsForCenter(id)
                .stream()
                .map(mapperService::MedicalStaffToAppUserDto)
                .toList();
        return ResponseEntity.ok(stuff);
    }
}
