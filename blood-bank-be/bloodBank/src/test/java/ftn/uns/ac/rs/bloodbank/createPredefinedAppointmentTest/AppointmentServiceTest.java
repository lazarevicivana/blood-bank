package ftn.uns.ac.rs.bloodbank.createPredefinedAppointmentTest;

import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;
    @Test(expected = PessimisticLockingFailureException.class)
    public void createAppointmentPessimisticLockingScenario() throws Throwable{
        //Arrange
        List<UUID> ids = new ArrayList<>();
        UUID id = UUID.fromString("34713840-ddf3-49b2-9cae-47334cb6b31d");
        ids.add(id);
        UUID centerId = UUID.fromString("ef81c6fc-bd01-4148-b460-b9f2eb7c53c1");
        var createAppointment1 =
                AppointmentRequest.builder()
                        .date(LocalDateTime.now().plusDays(1))
                        .startTime(LocalTime.now().plusHours(1))
                        .finishTime(LocalTime.now().plusHours(2))
                        .medicalStaffs(ids)
                        .centerId(centerId)
                        .build();
        var createAppointment2 =
                AppointmentRequest.builder()
                        .date(LocalDateTime.now().plusDays(1))
                        .startTime(LocalTime.now().plusHours(1))
                        .finishTime(LocalTime.now().plusHours(2))
                        .medicalStaffs(ids)
                        .centerId(centerId)
                        .build();
        //Act
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            System.out.println("Thread 1 started");
            appointmentService.createAppointment(createAppointment1); // izvrsavanje transakcione metode traje oko 200 milisekundi
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Thread 2 started");
                try { Thread.sleep(50); } catch (InterruptedException ignored) { }// otprilike 150 milisekundi posle prvog threada krece da se izvrsava drugi
                appointmentService.createAppointment(createAppointment2);
            }
        });
        //Assert
        try {
            future2.get(); // podize ExecutionException za bilo koji izuzetak iz drugog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas PessimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
