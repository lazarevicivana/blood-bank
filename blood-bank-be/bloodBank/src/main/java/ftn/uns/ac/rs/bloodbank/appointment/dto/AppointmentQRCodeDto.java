package ftn.uns.ac.rs.bloodbank.appointment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ftn.uns.ac.rs.bloodbank.appointment.model.AppointmentStatus;
import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentQRCodeDto implements Serializable {
    private UUID id;
    private UUID customerId;
    private AppointmentStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    @Column(length = 100000)
    private String QRCode;

    public AppointmentQRCodeDto(UUID id, UUID customerId, LocalDateTime date, AppointmentStatus status, String QRCode){
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.date = date;
        this.QRCode = QRCode;
    }
}
