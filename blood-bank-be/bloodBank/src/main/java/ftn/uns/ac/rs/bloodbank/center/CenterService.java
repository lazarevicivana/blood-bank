package ftn.uns.ac.rs.bloodbank.center;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterService {
    private final CenterRepository centerRepository;
    public List<Center> getAllCenters(){
        return centerRepository.findAll();
    }

}
