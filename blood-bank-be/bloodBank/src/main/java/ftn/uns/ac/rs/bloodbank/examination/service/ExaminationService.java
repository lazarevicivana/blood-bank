package ftn.uns.ac.rs.bloodbank.examination.service;

import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.appointment.repository.ScheduleAppointmentRepository;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodDonation;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodDonationRepository;
import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.customer.repository.CustomerRepository;
import ftn.uns.ac.rs.bloodbank.examination.dto.ExaminationDto;
import ftn.uns.ac.rs.bloodbank.examination.model.Examination;
import ftn.uns.ac.rs.bloodbank.examination.repository.ExaminationRepository;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ExaminationService {
    private final ExaminationRepository examinationRepository;
    private final ScheduleAppointmentRepository scheduleAppointmentRepository;
    private final CustomerRepository customerRepository;
    private final BloodDonationRepository bloodDonationRepository;
    @Transactional
    public void createExamination(ExaminationDto examinationDto){
       var appointment = scheduleAppointmentRepository.
               findById(examinationDto.getAppointmentId())
               .orElseThrow(()-> new ApiNotFoundException("Appointment not found"));
       if(!examinationDto.getIsAppeared()){
           calculatePenaltyForPatient(appointment.getCustomer());
           saveExaminationWithoutDonation(examinationDto, appointment);
           return;
       }
       if(!examinationDto.getIsSuitableBloodDonor()){
           saveExaminationWithoutDonation(examinationDto, appointment);
           return;
       }
       var newBloodDonation = BloodDonation
               .builder()
               .bloodType(examinationDto.getBloodDonation().getBloodType())
               .bloodUnit(examinationDto.getBloodDonation().getBloodUnit())
               .build();
       var bloodDonation = bloodDonationRepository.save(newBloodDonation);
       var examination = Examination
               .builder()
               .appointment(appointment)
               .bloodDonation(bloodDonation)
               .isAppeared(examinationDto.getIsAppeared())
               .isSuitableBloodDonor(examinationDto.getIsSuitableBloodDonor())
               .build();
       examinationRepository.save(examination);
    }

    private void saveExaminationWithoutDonation(ExaminationDto examinationDto, ScheduleAppointment appointment) {
        var examination = Examination
                .builder()
                .appointment(appointment)
                .bloodDonation(null)
                .isAppeared(examinationDto.getIsAppeared())
                .isSuitableBloodDonor(examinationDto.getIsSuitableBloodDonor())
                .build();
        examinationRepository.save(examination);
    }

    void calculatePenaltyForPatient(Customer customer){
        var updateCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(()-> new ApiNotFoundException("Customer not found"));
        var penalty = updateCustomer.getPenalty() + 1;
        updateCustomer.setPenalty(penalty);
        customerRepository.save(updateCustomer);
    }
}
