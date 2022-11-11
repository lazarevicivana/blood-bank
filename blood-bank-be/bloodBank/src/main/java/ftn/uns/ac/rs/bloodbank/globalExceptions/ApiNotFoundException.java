package ftn.uns.ac.rs.bloodbank.globalExceptions;

public class ApiNotFoundException extends RuntimeException{
    public ApiNotFoundException() {
    }

    public ApiNotFoundException(String message) {
        super(message);
    }
}
