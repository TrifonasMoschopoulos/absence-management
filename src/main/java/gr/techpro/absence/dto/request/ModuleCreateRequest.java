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
    @Pattern(
        regexp="\\S{5,}",
        message="Module code must contain at least 5 characters and must not contain spaces"    
    )
    @Schema(description="""
        Human-readable unique identifier for the module. 
        Used as a stable user-friendly reference.
        Unlike the internal database ID, this code remains consistent across different environments. 
        Rules for code: 
        1. It must not be empty or null
        2. It must not contain empty characters (space) 
        3. It must contain at least 5 characters 
    """, example="CS101")
    private String code;
    
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

    public String getCode() { return this.code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getCredits() { return this.credits; }
    public void setCredits(Integer credits) { this.credits = credits;}

    public String getSemester() { return this.semester; }
    public void setSemester(String semester) { this.semester = semester;}

    public Integer getAcademicYear() { return this.academicYear; }
    public void setAcademicYear(Integer academicYear) { this.academicYear = academicYear; }

}
