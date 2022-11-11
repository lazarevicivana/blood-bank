package ftn.uns.ac.rs.bloodbank.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest implements Serializable {
    @NonNull
    private  LocalDateTime date;
    @NonNull
    private LocalTime startTime;
    @NonNull
    private  LocalTime finishTime;
    private  List<UUID>  medical_stuff;
    private  UUID centerId;
}
