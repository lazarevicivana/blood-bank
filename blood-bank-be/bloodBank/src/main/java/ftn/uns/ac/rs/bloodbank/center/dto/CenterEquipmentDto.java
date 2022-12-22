package ftn.uns.ac.rs.bloodbank.center.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterEquipmentDto implements Serializable {
	private String equipmentName;
	private Integer quantity;
}
