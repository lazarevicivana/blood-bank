package ftn.uns.ac.rs.bloodbank.globalExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseEntity<ErrorResponse> handleNotFoundException(ApiNotFoundException ex)
    {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ApiBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ErrorResponse> handleBadRequestException(ApiBadRequestException ex)
    {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ApiConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ResponseEntity<ErrorResponse> handleConflictException(ApiConflictException ex)
    {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage()),HttpStatus.CONFLICT);
    }
}
