package ftn.uns.ac.rs.bloodbank.center;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Center")
@Table(name = "center", uniqueConstraints= {@UniqueConstraint(columnNames = {"name"})})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Center {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @Column(name = "name",nullable = false,columnDefinition = "TEXT")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private CenterAddress centerAddress;
    @Column(name = "description",nullable = false,columnDefinition = "TEXT")
    private String description;
    @Column(name = "avg_grade",nullable = false)
    private Double avgGrade;

    public Center( String name, CenterAddress centerAddress, String description, Double avgGrade) {
        this.name = name;
        this.centerAddress = centerAddress;
        this.description = description;
        this.avgGrade = avgGrade;
    }

}
