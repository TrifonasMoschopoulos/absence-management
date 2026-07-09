package gr.techpro.absence.dto.response;

import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String path;

    public ErrorResponse(HttpStatus status, String message, HttpServletRequest request){
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = request.getRequestURI();
    }

    public LocalDateTime getTimestamp() { return this.timestamp; }
    public HttpStatus getStatus() { return this.status; }
    public String getMessage() { return this.message; }
    public String getPath() { return this.path; }

}
