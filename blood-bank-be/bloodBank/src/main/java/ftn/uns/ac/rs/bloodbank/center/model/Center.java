package ftn.uns.ac.rs.bloodbank.center.model;
import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import lombok.*;
import javax.persistence.*;
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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "working_time_id",referencedColumnName = "id")
    private CenterWorkingTime centerWorkingTime;
    @OneToMany(mappedBy = "center")
    private Set<CenterEquipment> centerEquipments;
    public Center( String name, CenterAddress centerAddress, String description, Double avgGrade) {
        this.name = name;
        this.centerAddress = centerAddress;
        this.description = description;
        this.avgGrade = avgGrade;
    }
    public void addAdmin(CenterAdministrator centerAdministrator){
        medicalStuff.add(centerAdministrator);
        centerAdministrator.setCenter(this);
    }
    public void addAppointment(Appointment appointment){
        availableAppointments.add(appointment);
        appointment.setCenter(this);
    }
}
