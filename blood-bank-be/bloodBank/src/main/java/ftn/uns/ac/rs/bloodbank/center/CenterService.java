package ftn.uns.ac.rs.bloodbank.center;

import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CenterService {
    private final CenterRepository centerRepository;
    public List<Center> getAllCenters(){
        return centerRepository.findAll();
    }

    public Center createCenter(Center center) {
        if(center.getName() != null){
            var centerExist = centerRepository.GetByName(center.getName());
            if(centerExist.isPresent()){
                throw  new ApiBadRequestException("This name is already taken");
            }
            centerRepository.save(center);
            return center;
        }
        return null;
    }

    public Center getCenter(UUID id) {
        return centerRepository
                .findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Center with id: " + id + "does not exist"));
    }
    @Transactional
    public void updateCenter(Center center){
        var currentCenter = getCenter(center.getId());
        if(center.getName() != null){
            var centerExist = centerRepository.GetByName(center.getName());
            if(centerExist.isPresent()){
                throw  new ApiBadRequestException("This name is already taken");
            }
            currentCenter.setName(center.getName());
        }
        if(center.getDescription() != null){
            currentCenter.setDescription(center.getDescription());
        }
        if(center.getCenterAddress().getCity() != null){
            currentCenter.getCenterAddress().setCity(center.getCenterAddress().getCity());
        }
        if(center.getCenterAddress().getCountry() != null){
            currentCenter.getCenterAddress().setCountry(center.getCenterAddress().getCountry());
        }
        if(center.getCenterAddress().getLatitude() != null){
            currentCenter.getCenterAddress().setLatitude(center.getCenterAddress().getLatitude());
        }
        if(center.getCenterAddress().getLatitude() != null){
            currentCenter.getCenterAddress().setLatitude(center.getCenterAddress().getLatitude());
        }
        if(center.getCenterAddress().getLongitude() != null){
            currentCenter.getCenterAddress().setLongitude(center.getCenterAddress().getLongitude());
        }
        if(center.getCenterAddress().getStreet() != null){
            currentCenter.getCenterAddress().setStreet(center.getCenterAddress().getStreet());
        }
        if(center.getCenterAddress().getStreetNumber() != null){
            currentCenter.getCenterAddress().setStreetNumber(center.getCenterAddress().getStreetNumber());
        }
    }
}
