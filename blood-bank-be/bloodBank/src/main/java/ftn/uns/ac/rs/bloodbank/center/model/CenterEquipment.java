package ftn.uns.ac.rs.bloodbank.center.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "CenterEquipment")
@Table(name = "center_equipment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CenterEquipment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @ManyToOne
    @JoinColumn(name="center_id")
    private Center center;
    @ManyToOne
    @JoinColumn(name="equipment_id")
    private Equipment equipment;
    @Column(name = "quantity")
    private Integer quantity;
}
