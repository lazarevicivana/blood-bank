package ftn.uns.ac.rs.bloodbank.globalExceptions;

public class ApiBadRequestException extends RuntimeException{
    public ApiBadRequestException() {
    }

    public ApiBadRequestException(String message) {
        super(message);
    }
}
