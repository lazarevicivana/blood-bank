package ftn.uns.ac.rs.bloodbank.common;

import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class DateTimeService {
    public String convertDateToString(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }
}
