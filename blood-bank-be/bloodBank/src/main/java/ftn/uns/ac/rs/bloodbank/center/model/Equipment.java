package ftn.uns.ac.rs.bloodbank.center.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;
@Entity(name = "Equipment")
@Table(name = "equipment", uniqueConstraints= {
        @UniqueConstraint(
                columnNames = {"name"}
        )})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Equipment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private String name;

}
