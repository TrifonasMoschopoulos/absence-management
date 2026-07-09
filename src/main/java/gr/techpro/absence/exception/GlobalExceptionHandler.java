package gr.techpro.absence.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import gr.techpro.absence.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), request);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(ResourceAlreadyExistsException exception, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage(), request);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMethodArgument(MethodArgumentNotValidException exception, HttpServletRequest request){
        Map<String, String> errors = exception.getBindingResult()
                                              .getFieldErrors()
                                              .stream()
                                              .collect( Collectors
                                                        .toMap(FieldError::getField, FieldError::getDefaultMessage, 
                                                        (message, newMessage) -> message + " & " + newMessage)
                                              );


        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, errors.toString(), request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(InvalidRequestException exception, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage(), request);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }
}
