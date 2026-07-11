package gr.techpro.absence.exception;

public class BusinessRuleViolationException extends RuntimeException{
    
    public BusinessRuleViolationException(String message){
        super(message);
    }
}
