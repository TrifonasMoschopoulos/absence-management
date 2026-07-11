package gr.techpro.absence.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


@Schema(description="Payload for creating a new module. All fields are mandatory")
public class ModuleCreateRequest {
    
    @NotBlank
    @Schema(description="Must contain at least one non-white character", example="Introduction to Java")
    private String title;

    @NotNull
    @Positive
    @Schema(description="Must be a positive number", example="5")
    private Integer credits;

    @NotBlank
    @Pattern(regexp="FALL|SPRING|SUMMER", message="Invalid semester. Accepted values: FALL, SPRING, SUMMER")
    @Schema(description="Accepted values are: 'FALL', 'SPRING', 'SUMMER'", example="FALL")
    private String semester;

    @NotNull
    @Min(value=1000, message="Academic year must be a 4-digit number")
    @Max(value=9999, message="Academic year must be a 4-digit number")
    @Schema(description="Must be a 4-digit number", example="2023")
    private Integer academicYear;

    public ModuleCreateRequest() {}

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getCredits() { return this.credits; }
    public void setCredits(Integer credits) { this.credits = credits;}

    public String getSemester() { return this.semester; }
    public void setSemester(String semester) { this.semester = semester;}

    public Integer getAcademicYear() { return this.academicYear; }
    public void setAcademicYear(Integer academicYear) { this.academicYear = academicYear; }

}
