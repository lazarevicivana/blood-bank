package ftn.uns.ac.rs.bloodbank.center;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/center")
public class CenterController {
    private final CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }
    public List<Center> getCenters(){
        return null;
    }
    @GetMapping
    public String hello(){
        return "hello";
    }
}
