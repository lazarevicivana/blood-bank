package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import com.sun.istack.NotNull;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoResponse;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
