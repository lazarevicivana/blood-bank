package ftn.uns.ac.rs.bloodbank.appointment.service;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.common.DateTimeService;
import ftn.uns.ac.rs.bloodbank.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@AllArgsConstructor
@Service
public class QRGeneratorService {
    private final EmailService emailService;
    private final DateTimeService dateTimeService;

    public void generateQRCodeImage(ScheduleAppointment scheduleAppointment, int width, int height, String filePath)
            throws WriterException, IOException {
        var appointmentString = serializeObject(scheduleAppointment);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(appointmentString, BarcodeFormat.QR_CODE, width, height);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ImageIO.write(image, "PNG", new File(filePath));
        emailService.sendEmailQRAppointment(filePath);
    }
    public String serializeObject(ScheduleAppointment scheduleAppointment) {
        StringBuilder sb = new StringBuilder();
        sb.append("Scheduled appointment").append("\n");
        sb.append("\n");
        sb.append("Donor : ").append(scheduleAppointment
                        .getCustomer()
                        .getName() + " " + scheduleAppointment
                        .getCustomer()
                        .getSurname())
                .append("\n");
        sb.append("Date : ")
                .append(dateTimeService.convertDateToString(scheduleAppointment
                        .getAppointment()
                        .getDate()))
                .append("\n");
        sb.append("Starts : ").append(scheduleAppointment
                        .getAppointment()
                        .getStartTime())
                        .append("\n");
        sb.append("Ends : ").append(scheduleAppointment
                        .getAppointment()
                        .getFinishTime())
                        .append("\n");
        sb.append("Center : ").append(scheduleAppointment
                        .getAppointment()
                        .getCenter()
                        .getName())
                        .append("\n");


        return sb.toString();
    }
}
