package ftn.uns.ac.rs.bloodbank.center.service;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodBank;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodType;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodBankRepository;
import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdminRepository;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiConflictException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CenterService {
    private final CenterRepository centerRepository;
    private final CenterAdminRepository centerAdminRepository;
    private final BloodBankRepository bloodBankRepository;
    public List<Center> getAllCenters(){

        return centerRepository.findAll();
    }

    public Center createCenter(Center center) {
        var centerExist = centerRepository.GetByName(center.getName());
        if(centerExist.isPresent()){
            throw  new ApiConflictException("This name is already taken");
        }
        Center saveCenter = centerRepository.save(center);
        CreateBloodBankForCenter(center);
        return saveCenter;
    }
    public  void CreateBloodBankForCenter(Center center) {
        for(BloodType bloodType: BloodType.values()){
            var bloodBank = BloodBank
                    .builder()
                    .bloodType(bloodType)
                    .center(center)
                    .bloodUnit(0)
                    .build();
            bloodBankRepository.save(bloodBank);
        }
    }
    public Center getCenter(UUID id) {
        return centerRepository
                .findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Center with id: " + id + "does not exist"));
    }
    @Transactional
    public void updateCenter(Center center){
        var currentCenter = getCenter(center.getId());
        if(center.getName() != null && !center.getName().toLowerCase(Locale.ROOT).
                equals(currentCenter.getName().toLowerCase(Locale.ROOT))){
            var centerExist = centerRepository.GetByName(center.getName());
            if(centerExist.isPresent()){
                throw  new ApiBadRequestException("Center name "+center.getName()+" is already taken.");
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
        centerRepository.save(currentCenter);
    }

    public List<CenterAdministrator> getAdminsForCenter(UUID id) {

        return centerRepository.GetAdmins(id);
    }
    public List<CenterAdministrator> getOtherCenterAdmins(UUID centerId,UUID adminId){
            var admin = centerAdminRepository.findById(adminId)
                    .orElseThrow(() -> new ApiNotFoundException());
            var otherAdmins = getAdminsForCenter(centerId)
                    .stream()
                    .filter((x) -> x.getId() != adminId)
                    .toList();
            return otherAdmins;
    }
}
