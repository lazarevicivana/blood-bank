package ftn.uns.ac.rs.bloodbank.center;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/center")
public class CenterController {
    private final CenterService centerService;
    @GetMapping(path="/all")
    public List<Center> getAllCenters(){
        return centerService.getAllCenters();
    }
    @PostMapping
    public void create(){

    }
}
