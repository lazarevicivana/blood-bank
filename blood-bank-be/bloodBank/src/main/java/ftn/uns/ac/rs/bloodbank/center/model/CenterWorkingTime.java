package ftn.uns.ac.rs.bloodbank.center.model;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "CenterWorkingTime")
@Table(name = "center_working_time")
@Getter
@Setter
public class CenterWorkingTime implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private LocalDateTime workingStartTime;
    private LocalDateTime workingFinishTime;


}
