package ftn.uns.ac.rs.bloodbank.appointment.model;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Appointment")
@Table(name = "appointment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "center_id", referencedColumnName = "id")
    private Center center;
    @ManyToMany
    @JoinTable(
            name = "appointment_stuff",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "stuff_id"))
    private Set<CenterAdministrator> medicalStaffs = new HashSet<>();
    private LocalDateTime date;
    private LocalTime startTime;
    private LocalTime finishTime;
    private Boolean deleted = false;
    public boolean isOverlappingDate(@NonNull Appointment other) {
        if (!other.getDate().toLocalDate().isEqual(date.toLocalDate())) {
            return false;
        }
        return !(other.getFinishTime().isBefore(startTime) || other.getStartTime().isAfter(finishTime));
    }
    public boolean isValidDate(){
        return date.isBefore(LocalDateTime.now());
    }
    public boolean isDeleted(){
        return deleted;
    }
    public boolean isValidDateTime(){
        return !startTime.isBefore(finishTime);
    }
}
