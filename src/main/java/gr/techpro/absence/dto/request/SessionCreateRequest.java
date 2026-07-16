package gr.techpro.absence.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import gr.techpro.absence.enums.SessionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description="Payload for creating a new session. The only optional field is 'topic'")
public class SessionCreateRequest {
    
    @NotNull
    @Schema(description="The Date on which the session took place (or will take place)", example="2026-11-22")
    private LocalDate sessionDate;

    @NotNull
    @Schema(description="The session's start time in 24-hour format: HH:MM:SS",  example="16:30:00")
    private LocalTime startTime;

    @NotNull
    @Schema(description="The session's end time in 24-hour format: HH:MM:SS",  example="19:15:00")
    private LocalTime endTime;

    @NotNull
    @Schema(description="The type of session. Accepted values are: 'LECTURE', 'LAB', 'SEMINAR'", example="SEMINAR")
    private SessionType type;

    @Size(max = 255, message="Topic must not exceed 255 characters")
    @Schema(description="This field is optional", example="Exceptions & Collection Framework")
    private String topic;

    
    public SessionCreateRequest() {}

    public LocalDate getSessionDate() { return this.sessionDate; }
    public LocalTime getStartTime() { return this.startTime; }
    public LocalTime getEndTime() { return this.endTime; }
    public SessionType getType() { return this.type; }
    public String getTopic() { return this.topic; }

    public void setSessionDate(LocalDate date) { this.sessionDate = date;}
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setType(SessionType type) { this.type = type; }
    public void setTopic(String topic) { this.topic = topic; }
}
