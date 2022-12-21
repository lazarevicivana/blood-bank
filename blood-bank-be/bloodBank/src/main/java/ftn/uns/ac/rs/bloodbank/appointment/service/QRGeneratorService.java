package ftn.uns.ac.rs.bloodbank.appointment.service;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.common.DateTimeService;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerQRCodeService;
import ftn.uns.ac.rs.bloodbank.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@AllArgsConstructor
@Service
public class QRGeneratorService {
    private final EmailService emailService;
    private final DateTimeService dateTimeService;
    private final CustomerQRCodeService customerQRCodeService;

    public void generateQRCodeImage(ScheduleAppointment scheduleAppointment, int width, int height, String filePath)
            throws WriterException, IOException {
        var appointmentString = serializeObject(scheduleAppointment);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(appointmentString, BarcodeFormat.QR_CODE, width, height);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ImageIO.write(image, "PNG", new File(filePath));
        var base64 = convertQRCodeToBase64(filePath);
        customerQRCodeService.createCustomerQRCode(base64,scheduleAppointment.getCustomer());
        emailService.sendEmailQRAppointment(base64);
    }
    public String convertQRCodeToBase64(String filePath)throws IOException{
        var path = Paths.get(filePath);
        return  Base64.getEncoder().encodeToString(Files.readAllBytes(path));
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
