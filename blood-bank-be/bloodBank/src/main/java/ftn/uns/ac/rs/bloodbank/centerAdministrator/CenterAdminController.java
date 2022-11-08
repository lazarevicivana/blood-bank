package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import com.sun.istack.NotNull;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterAdministratorDto;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDto;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoResponse;
import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/center-admin")
@RequiredArgsConstructor
public class CenterAdminController {
    private final CenterAdminService centerAdminService;
    private final MapperService mapperService;

    @GetMapping(path = "center/{id}")
    public ResponseEntity<CenterDtoResponse> getCenter(@NotNull @PathVariable("id") UUID id) {
        var center =mapperService.CenterToCenterDto(centerAdminService.GetAdminCenter(id));
        return ResponseEntity.ok(center);
    }
    @PutMapping(path = "updateCenter/{id}")
    public ResponseEntity updateCenter(@NotNull @PathVariable("id") UUID id,@RequestBody Center center) {
        CenterAdministrator admin = null;
        try {
            admin = centerAdminService.getCenterAdministrator(id);
            admin.setCenter(center);
            return new ResponseEntity<CenterAdministrator>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CenterAdministrator>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping()
    public ResponseEntity<CenterAdministrator> createCenterAdministrator(@RequestBody CenterAdministratorDto centerAdministratorDto){
        var adress = mapperService.AdressRequestToAdress(centerAdministratorDto.getAddressRequest());
        CenterAdministrator centerAdministrator = mapperService.CenterAdministratorDtoToCenterAdministrator(centerAdministratorDto);
        centerAdministrator.setAddress(adress);
        CenterAdministrator savedCenterAdministrator =centerAdminService.createCenterAdministrator(centerAdministrator);
        return new ResponseEntity<CenterAdministrator>(savedCenterAdministrator, HttpStatus.CREATED);
    }
}
