package ftn.uns.ac.rs.bloodbank.mapper;


import ftn.uns.ac.rs.bloodbank.applicationUser.dto.ApplicationUserUpdate;
import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;



import ftn.uns.ac.rs.bloodbank.applicationUser.dto.ApplicationUserDtoResponse;
import ftn.uns.ac.rs.bloodbank.appointment.dto.MedicalStaffResponse;
import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDto;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoResponse;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoUpdate;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.dto.CenterAdministratorDto;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.dto.CenterAdministratorDtoResponse;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerFormRequest;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerFormResponse;
import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.customer.model.CustomerForm;
import ftn.uns.ac.rs.bloodbank.customer.model.Profession;
import ftn.uns.ac.rs.bloodbank.registration.dto.AddressRequest;
import ftn.uns.ac.rs.bloodbank.registration.dto.CustomerRequest;
import ftn.uns.ac.rs.bloodbank.registration.dto.ProfessionRequest;
import ftn.uns.ac.rs.bloodbank.sharedModel.Address;
import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import ftn.uns.ac.rs.bloodbank.systemAdministrator.SystemAdministrator;
import ftn.uns.ac.rs.bloodbank.systemAdministrator.dto.SystemAdministratorDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class MapperService {
    private final ModelMapper modelMapper;

    public MapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public ApplicationUserDtoResponse AppUserToAppUserDto(ApplicationUser applicationUser){
        return modelMapper.map(applicationUser,ApplicationUserDtoResponse.class);
    }
    public ApplicationUserDtoResponse CustomerToAppUserDto(Customer customer){
        return modelMapper.map(customer,ApplicationUserDtoResponse.class);
    }
//    public ApplicationUserDtoResponse MedicalStaffToAppUserDto(CenterAdministrator applicationUser){
//        return modelMapper.map(applicationUser,ApplicationUserDtoResponse.class);
//    }

    public ApplicationUserUpdate AppUserToAppUserUpdate(ApplicationUser applicationUser){
        return modelMapper.map(applicationUser,ApplicationUserUpdate.class);
    }


    public ApplicationUser AppUserUpdateToAppUser(ApplicationUserUpdate applicationUserUpdate){
        return modelMapper.map(applicationUserUpdate,ApplicationUser.class);
    }
    public CenterDtoResponse CenterToCenterDto(Center center){
        return modelMapper.map(center,CenterDtoResponse.class);
    }
    public CenterAdministratorDto CenterAdministratorToCenterAdministratorDto(CenterAdministrator centerAdministrator){
        return modelMapper.map(centerAdministrator,CenterAdministratorDto.class);
    }

    public Center CenterDtoToCenter(CenterDto centerDto){
        return modelMapper.map(centerDto,Center.class);
    }
    public Center CenterDtoUpdateToCenter(CenterDtoUpdate centerDto){
        return modelMapper.map(centerDto,Center.class);
    }
    public Customer CustomerRequestToCustomer(CustomerRequest request) {return  modelMapper.map(request, Customer.class);}
    public Address AdressRequestToAdress(AddressRequest request){return modelMapper.map(request,Address.class);}
    public Profession ProfessionRequestToProfession(ProfessionRequest request){return modelMapper.map(request,Profession.class);}
    public CenterAdministrator CenterAdministratorDtoToCenterAdministrator(CenterAdministratorDto dto) {return  modelMapper.map(dto, CenterAdministrator.class);}
    public CenterAdministratorDtoResponse CenterAdministratorToCenterAdministratorDtoResponse(CenterAdministrator centerAdministrator){
        return modelMapper.map(centerAdministrator,CenterAdministratorDtoResponse.class);
    }
    public MedicalStaffResponse MedicalStaffToAppUserDto(CenterAdministrator applicationUser){
        return modelMapper.map(applicationUser, MedicalStaffResponse.class);
    }
    public CustomerForm CustomerFormDtoToCustomerForm(CustomerFormRequest customerFormRequest){
        return modelMapper.map(customerFormRequest,CustomerForm.class);
    }
    public CustomerFormResponse CustomerFormToCustomerFormDto(CustomerForm customerForm){
        return modelMapper.map(customerForm,CustomerFormResponse.class);
    }
    public ScheduleAppointment ScheduleAppointmentDtoToScheduleAppointment(ScheduleAppointmentRequest request)
    {
        return modelMapper.map(request, ScheduleAppointment.class);
    }
    public SystemAdministrator SystemAdministratorDtoToSystemAdministrator(SystemAdministratorDto dto) {return  modelMapper.map(dto, SystemAdministrator.class);}
}
