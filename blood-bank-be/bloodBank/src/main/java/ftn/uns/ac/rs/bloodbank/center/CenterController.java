package ftn.uns.ac.rs.bloodbank.center;

import com.sun.istack.NotNull;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/center")
public class CenterController {
    private final CenterService centerService;
    private final MapperService mapperService;

    public CenterController(CenterService centerService, ModelMapper modelMapper, MapperService mapperService) {
        this.centerService = centerService;
        this.mapperService = mapperService;
    }

    @GetMapping()
    public List<CenterDtoResponse> getAllCenters(){
        return centerService.getAllCenters()
                .stream()
                .map(mapperService::CenterToCenterDto)
                .toList();
    }
    @PostMapping
    public void createCenter(@RequestBody CenterDto centerDto){
        Center center = mapperService.CenterDtoToCenter(centerDto);
        centerService.createCenter(center);
    }
    @GetMapping(path = "{id}")
    public CenterDtoResponse getCenter(@NotNull @PathVariable("id") UUID id) {
        return mapperService.CenterToCenterDto(centerService.getCenter(id));
    }
}
