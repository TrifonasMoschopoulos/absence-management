package gr.techpro.absence.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import gr.techpro.absence.enums.SessionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Session {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="module_id", nullable=false)
    private Module module;

    @Column(name="session_date", nullable=false)
    private LocalDate sessionDate;

    @Column(name="start_time", nullable=false)
    private LocalTime startTime;

    @Column(name="end_time", nullable=false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name="session_type", length=20, nullable=false)
    private SessionType type = SessionType.LECTURE;

    @Column(name="topic", length=255)
    private String topic;


    public Session() { }



    public Session(Module module, LocalDate sessionDate, LocalTime startTime, LocalTime endTime, SessionType type){
        this.module = module;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;   
    }

    public Session(Module module, LocalDate sessionDate, LocalTime startTime, LocalTime endTime, SessionType type, String topic){
        this(module, sessionDate, startTime, endTime, type);
        this.topic = topic;
    }

    


    public Long getId() { return this.id; }
    public Module getModule() { return this.module; }
    public LocalDate getSessionDate() { return this.sessionDate; }
    public LocalTime getStartTime() { return this.startTime; }
    public LocalTime getEndTime() { return this.endTime; }
    public SessionType getType() { return this.type; }
    public String getTopic() { return this.topic; }

    public void setTopic(String topic) { this.topic = topic; }

}
