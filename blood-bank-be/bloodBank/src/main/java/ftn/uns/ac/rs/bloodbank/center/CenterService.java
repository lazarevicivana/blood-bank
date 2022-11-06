package ftn.uns.ac.rs.bloodbank.center;

import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CenterService {
    private final CenterRepository centerRepository;
    public List<Center> getAllCenters(){
        return centerRepository.findAll();
    }

    public void createCenter(Center center) {
        centerRepository.save(center);
    }

    public Center getCenter(UUID id) {
        return centerRepository
                .findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Center with id: " + id + "does not exist"));
    }
}
