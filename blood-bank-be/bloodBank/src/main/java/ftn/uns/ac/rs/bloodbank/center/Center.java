package ftn.uns.ac.rs.bloodbank.center;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class Center {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private CenterAddress centerAddress;
    private String description;
    private Double avgGrade;

    public Center(UUID id, String name, CenterAddress centerAddress, String description, Double avgGrade) {
        this.id = id;
        this.name = name;
        this.centerAddress = centerAddress;
        this.description = description;
        this.avgGrade = avgGrade;
    }
    public Center( String name, CenterAddress centerAddress, String description, Double avgGrade) {
        this.name = name;
        this.centerAddress = centerAddress;
        this.description = description;
        this.avgGrade = avgGrade;
    }

    public Center() {

    }
}
