package gr.techpro.absence.dto.response.session;

import java.time.LocalDate;
import java.time.LocalTime;

import gr.techpro.absence.enums.SessionType;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description="Session details response containing all available information about a module's session")
public class SessionResponse {

    @Schema(description="Surrogate (db) key", example="32")
    private Long id;

    @Schema(description="The Date on which the session took place (or will take place)", example="2026-11-22")
    private LocalDate sessionDate;

    @Schema(description="The session's start time in 24-hour format: HH:MM:SS",  example="16:30:00")
    private LocalTime startTime;

    @Schema(description="The session's end time in 24-hour format: HH:MM:SS",  example="19:15:00")
    private LocalTime endTime;

    @Schema(description="The type of session. Accepted values are: 'LECTURE', 'LAB', 'SEMINAR'", example="SEMINAR")
    private SessionType type;

    @Schema(example="Exceptions & Collection Framework")
    private String topic;

    public SessionResponse() {}

    public SessionResponse(Long id, LocalDate sessionDate, LocalTime startTime, LocalTime endTime, SessionType type, String topic){
        this.id = id;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.topic = topic;
    }

    public Long getId() { return this.id; }
    public LocalDate getSessionDate() { return this.sessionDate; }
    public LocalTime getStartTime() { return this.startTime; }
    public LocalTime getEndTime() { return this.endTime; }
    public SessionType getType() { return this.type; }
    public String getTopic() { return this.topic; }
}
