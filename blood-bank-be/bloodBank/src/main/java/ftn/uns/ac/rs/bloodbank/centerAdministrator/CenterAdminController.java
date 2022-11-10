package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import com.sun.istack.NotNull;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.dto.CenterAdministratorDto;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.dto.CenterAdministratorDtoResponse;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoResponse;
import ftn.uns.ac.rs.bloodbank.center.service.CenterService;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/center-admin")
@RequiredArgsConstructor
public class CenterAdminController {
    private final CenterAdminService centerAdminService;
    private final CenterService centerService;
    private final MapperService mapperService;

    @GetMapping(path = "center/{id}")
    public ResponseEntity<CenterDtoResponse> getCenter(@NotNull @PathVariable("id") UUID id) {
        var center =mapperService.CenterToCenterDto(centerAdminService.GetAdminCenter(id));
        return ResponseEntity.ok(center);
    }

    @PutMapping(path = "updateCenter/{adminId}/{centerId}")
    public ResponseEntity<CenterAdministrator> updateAdministratorCenter(@NotNull @PathVariable("adminId") UUID adminId,@NotNull @PathVariable("centerId") UUID centerId) {
        var center = centerService.getCenter(centerId);
        centerAdminService.updateAdministratorCenter(adminId,center);
        return new ResponseEntity<CenterAdministrator>(HttpStatus.NO_CONTENT);
    }
    @PostMapping()
    public ResponseEntity<String> createCenterAdministrator(@RequestBody CenterAdministratorDto centerAdministratorDto){
        //var address = mapperService.AdressRequestToAdress(centerAdministratorDto.getAddressRequest());
        //CenterAdministrator centerAdministrator = mapperService.CenterAdministratorDtoToCenterAdministrator(centerAdministratorDto);
        //centerAdministrator.setAddress(address);
        centerAdminService.createCenterAdministrator(centerAdministratorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "availableAdmins")
    public ResponseEntity<List<CenterAdministratorDtoResponse>> getAvailableAdmins() {
        var admins = centerAdminService.getAvailableAdmins()
                .stream()
                .map(mapperService::CenterAdministratorToCenterAdministratorDtoResponse)
                .toList();

        return ResponseEntity.ok(admins);
    }
}
