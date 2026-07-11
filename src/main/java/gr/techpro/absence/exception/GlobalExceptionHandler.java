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
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
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


    @ExceptionHandler(BusinessRuleViolationException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleViolation(BusinessRuleViolationException exception, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage(), request);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException exception, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    
    // DTO EXCEPTION HANDLING. ACTIVATED AT @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMethodArgument(MethodArgumentNotValidException exception, HttpServletRequest request){
        Map<String, String> errors = exception.getBindingResult()
                                              .getFieldErrors()
                                              .stream()
                                              .collect( Collectors
                                                        .toMap(FieldError::getField, FieldError::getDefaultMessage, 
                                                        (message, newMessage) -> message + " & " + newMessage)
                                              );


        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, errors, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    // PATH VARIABLE AND QUERY PARAM EXCEPTION HANDLING
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException exception, HttpServletRequest request){
        
        // Extract all Errors into a Map containing entries: (field_name, error_message)
        Map<String, String> errors = exception.getConstraintViolations()
                 .stream()
                 .collect(Collectors.toMap(
                    v -> {
                        Path path = v.getPropertyPath();
                        String fieldName = "";
                        for (Path.Node node : path){
                            fieldName = node.getName();
                        }
                        return fieldName;
                    }, 
                    ConstraintViolation::getMessage
                ));
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, errors, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }



    
}
