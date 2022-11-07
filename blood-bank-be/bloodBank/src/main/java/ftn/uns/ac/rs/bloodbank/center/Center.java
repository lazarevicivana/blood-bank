package ftn.uns.ac.rs.bloodbank.center;

import ftn.uns.ac.rs.bloodbank.appointment.Appointment;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Center")
@Table(name = "center", uniqueConstraints= {
        @UniqueConstraint(
                columnNames = {"name"}
        )})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Center {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @Column(name = "name",nullable = false,columnDefinition = "TEXT",unique = true)
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private CenterAddress centerAddress;
    @Column(name = "description",nullable = false,columnDefinition = "TEXT")
    private String description;
    @Column(name = "avg_grade",nullable = false)
    private Double avgGrade;
    @OneToMany(mappedBy="center")
    private Set<CenterAdministrator> medicalStuff;
    @OneToMany(mappedBy="center")
    private Set<Appointment> availableAppointments;
    @ManyToMany
    @JoinTable(
            name = "center_working",
            joinColumns = @JoinColumn(name = "center_id"),
            inverseJoinColumns = @JoinColumn(name = "working_time_id"))
    private Set<CenterWorkingTime> centerWorkingTime;
    @ManyToMany
    @JoinTable(
            name = "center_equipment",
            joinColumns = @JoinColumn(name = "center_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<Equipment> equipment;
    public Center( String name, CenterAddress centerAddress, String description, Double avgGrade) {
        this.name = name;
        this.centerAddress = centerAddress;
        this.description = description;
        this.avgGrade = avgGrade;
    }

}
