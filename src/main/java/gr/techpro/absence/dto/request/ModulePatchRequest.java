package gr.techpro.absence.dto.request;

import gr.techpro.absence.enums.Semester;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Schema(description="Payload for partially updating an existing module. At least one field must be given. All provided fields must be valid")
public class ModulePatchRequest {
    
    @Pattern(regexp=".*\\S.*", message="At least one non-whitespace character must be given")
    @Schema(description="Must contain at least one non-whitespace character", example="Computer Science fundamentals")
    private String title;
    
    @Positive(message="Credits must be a positive number")
    @Schema(description="Credits must be a positive number", example="4")
    private Integer credits;
    
    @Schema(description="Accepted values are: 'FALL', 'SPRING', 'SUMMER'", example="SPRING")
    private Semester semester;

    @Min(value=1000, message="Academic year must be a 4-digit number")
    @Max(value=9999, message="Academic year must be a 4-digit number")
    @Schema(description="Must be a 4-digit number", example="2025")
    private Integer academicYear;

    public ModulePatchRequest() {}

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getCredits() { return this.credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public Semester getSemester() { return this.semester; }
    public void setSemester(Semester semester) { this.semester = semester; }

    public Integer getAcademicYear() { return this.academicYear; }
    public void setAcademicYear(Integer academicYear) { this.academicYear = academicYear; }
}
