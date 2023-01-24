package ftn.uns.ac.rs.bloodbank.schedulePredefineAppointment;

import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.service.ScheduleAppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ScheduleAppointmentServiceTest {
    @Autowired
    private ScheduleAppointmentService scheduleAppointmentService;
    @Test(expected = PessimisticLockingFailureException.class)
    public void createScheduleAppointmentPessimisticLockingScenario() throws Throwable{
        //Arrange
        List<UUID> ids = new ArrayList<>();
        UUID id = UUID.fromString("75b3388c-3d1d-4a1d-b1d6-c1c86298d932");
        UUID customerId1 = UUID.fromString("a0e141aa-62a9-11ed-9b6a-0242ac120002");
        UUID customerId2 = UUID.fromString("0dcce2a4-62b6-11ed-9b6a-0242ac120002");
        ids.add(id);
        var createScheduleAppointment1 =
                ScheduleAppointmentRequest.builder()
                        .appointment_id(id)
                        .customer_id(customerId1)
                        .build();
        var createScheduleAppointment2 =
                ScheduleAppointmentRequest.builder()
                        .appointment_id(id)
                        .customer_id(customerId2)
                        .build();
        //Act
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            System.out.println("Thread 1 started");
            scheduleAppointmentService.createScheduleAppointment(createScheduleAppointment1); // izvrsavanje transakcione metode traje oko 200 milisekundi
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Thread 2 started");
                try { Thread.sleep(50); } catch (InterruptedException ignored) { }// otprilike 150 milisekundi posle prvog threada krece da se izvrsava drugi
                scheduleAppointmentService.createScheduleAppointment(createScheduleAppointment2);
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
