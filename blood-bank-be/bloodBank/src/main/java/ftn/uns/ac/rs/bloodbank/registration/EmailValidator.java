package ftn.uns.ac.rs.bloodbank.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String email) {
        //TODO: regex
        return true;
    }
}
