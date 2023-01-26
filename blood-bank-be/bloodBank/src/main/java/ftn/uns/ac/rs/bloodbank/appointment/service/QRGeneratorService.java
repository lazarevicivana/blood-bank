package ftn.uns.ac.rs.bloodbank.appointment.service;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentIdResponse;
import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentQRCodeDto;
import ftn.uns.ac.rs.bloodbank.appointment.model.AppointmentStatus;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.appointment.repository.ScheduleAppointmentRepository;
import ftn.uns.ac.rs.bloodbank.common.DateTimeService;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerQRCodeService;
import ftn.uns.ac.rs.bloodbank.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

@AllArgsConstructor
@Service
public class QRGeneratorService {
    private final EmailService emailService;
    private final DateTimeService dateTimeService;
    private final CustomerQRCodeService customerQRCodeService;
    private final ScheduleAppointmentRepository scheduleAppointmentRepository;

    public void generateQRCodeImage(ScheduleAppointment scheduleAppointment, int width, int height, String filePath)
            throws WriterException, IOException {
        var appointmentString = serializeObject(scheduleAppointment);
        System.out.println(appointmentString);
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
        sb.append("Id : ").append(scheduleAppointment
                .getId());
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

    public String handleFileUpload(MultipartFile file) throws IOException, ChecksumException, NotFoundException, FormatException {
        // convert the multipart file to a File object
        File qrImage = convertMultiPartToFile(file);

        // create a QR code reader
        QRCodeReader qrCodeReader = new QRCodeReader();
        BufferedImage qrImageBuffered = ImageIO.read(qrImage);

        // decode the QR code
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(qrImageBuffered)));
        Result qrCodeResult = qrCodeReader.decode(binaryBitmap);

        // get the QR code text
        String qrCodeText = qrCodeResult.getText();
        //System.out.println(qrCodeText);
        String finalText = qrCodeText.split(":")[1].split("\n")[0].trim();

        System.out.println(finalText);
        return finalText;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public ArrayList<AppointmentQRCodeDto> getCustomerCodes(UUID customerId) throws IOException, ChecksumException, NotFoundException, FormatException {
        var customerCodes = customerQRCodeService.getCustomerCodes(customerId);
        ArrayList<AppointmentQRCodeDto> p = new ArrayList<>();
        for(int i=0; i!=customerCodes.size();i++){
            var code = customerCodes.get(i);
            var scheduleId = handleFileUpload(Base64ToMultipartFile(code.getQRCode()));
            var scheduleApp =scheduleAppointmentRepository.findById(UUID.fromString(scheduleId));
            p.add(new AppointmentQRCodeDto(UUID.fromString(scheduleId), code.getCustomer().getId(), scheduleApp.get().getAppointment().getDate(), scheduleApp.get().getStatus(),code.getQRCode()));
        }

        return p;
    }

    private MultipartFile Base64ToMultipartFile(String base64) throws IOException {
        byte[] byteArray = Base64.getDecoder().decode(base64);
        ByteArrayResource byteArrayResource = new ByteArrayResource(byteArray){
            @Override
            public String getFilename(){
                return "qqrr";
            }
        };
        MultipartFile multipartFile = new MockMultipartFile("qqrr", byteArrayResource.getFilename(), "application/octet-stream", byteArrayResource.getInputStream());
        return multipartFile;
    }

}
