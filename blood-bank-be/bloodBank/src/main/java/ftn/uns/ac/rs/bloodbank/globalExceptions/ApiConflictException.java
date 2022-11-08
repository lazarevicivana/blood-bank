package ftn.uns.ac.rs.bloodbank.globalExceptions;

public class ApiConflictException extends RuntimeException{
    public ApiConflictException() {
        super();
    }

    public ApiConflictException(String message) {
        super(message);
    }
}
